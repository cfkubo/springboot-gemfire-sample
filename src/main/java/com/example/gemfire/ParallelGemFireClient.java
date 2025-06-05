package com.example.gemfire;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ParallelGemFireClient {

    private static final int NUM_THREADS = 10;
    private static final int OPS_PER_THREAD = 10000; // 10,000 ops per thread * 10 threads = 100,000 total ops
    private static final String REGION_NAME = "myRegion";
    private static final String LOCATOR_HOST = "gemfire-cluster-gemfire-locator-0-1";
    private static final int LOCATOR_PORT = 10334;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Connecting to GemFire...");
        ClientCache cache = null;
        try {
            cache = new ClientCacheFactory()
                    .addPoolLocator(LOCATOR_HOST, LOCATOR_PORT)
                    .create();
            System.out.println("GemFire connection SUCCESS.");
        } catch (Exception e) {
            System.err.println("GemFire connection FAILED: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        Region<String, String> region = cache.getRegion(REGION_NAME);

        if (region == null) {
            System.out.println("Region '" + REGION_NAME + "' not found. Creating it as PROXY...");
            ClientRegionFactory<String, String> regionFactory =
                    cache.createClientRegionFactory(ClientRegionShortcut.PROXY);
            region = regionFactory.create(REGION_NAME);
            System.out.println("Region '" + REGION_NAME + "' created.");
            // Wait 10 seconds for region to be ready
            System.out.println("Waiting 10 seconds for region to be ready...");
            Thread.sleep(10_000);
        }

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        AtomicLong totalPuts = new AtomicLong(0);
        AtomicLong totalGets = new AtomicLong(0);

        final Region<String, String> regionRef = region;

        System.out.println("Starting " + NUM_THREADS + " threads for parallel PUT operations...");
        long startTimePuts = System.nanoTime();
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < OPS_PER_THREAD; j++) {
                    String key = "key-" + threadId + "-" + j;
                    String value = "value-" + System.nanoTime();
                    regionRef.put(key, value);
                    System.out.printf("PUT: %s -> %s\n", key, value);
                    long count = totalPuts.incrementAndGet();
                    if (count % 10000 == 0) {
                        System.out.printf("PUT progress: %d records inserted\n", count);
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // Wait for puts to complete
        long endTimePuts = System.nanoTime();
        double durationPutsSeconds = (double)(endTimePuts - startTimePuts) / 1_000_000_000.0;
        System.out.printf("Finished %d parallel PUTs in %.2f seconds (%.2f ops/sec)\n",
                          totalPuts.get(), durationPutsSeconds, totalPuts.get() / durationPutsSeconds);

        // Print region size after puts
        try {
            int size = region.size();
            System.out.println("Region size after PUTs: " + size);
        } catch (Exception e) {
            System.out.println("Could not determine region size: " + e.getMessage());
        }

        // Reset executor for gets
        executor = Executors.newFixedThreadPool(NUM_THREADS);
        final Region<String, String> regionRefForGets = region;

        System.out.println("Starting " + NUM_THREADS + " threads for parallel GET operations...");
        long startTimeGets = System.nanoTime();
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            executor.submit(() -> {
                for (int j = 0; j < OPS_PER_THREAD; j++) {
                    String key = "key-" + threadId + "-" + j;
                    String value = regionRefForGets.get(key);
                    System.out.printf("GET: %s -> %s\n", key, value);
                    long count = totalGets.incrementAndGet();
                    if (count % 1000 == 0) {
                        System.out.printf("GET progress: %d records read\n", count);
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // Wait for gets to complete
        long endTimeGets = System.nanoTime();
        double durationGetsSeconds = (double)(endTimeGets - startTimeGets) / 1_000_000_000.0;
        System.out.printf("Finished %d parallel GETs in %.2f seconds (%.2f ops/sec)\n",
                          totalGets.get(), durationGetsSeconds, totalGets.get() / durationGetsSeconds);

        cache.close();
        System.out.println("Disconnected from GemFire.");
    }
}