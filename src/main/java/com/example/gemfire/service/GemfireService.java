package com.example.gemfire.service;

import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Service
public class GemfireService {

    private final Region<String, String> region;

    public GemfireService() {
        ClientCache clientCache = new ClientCacheFactory().create();
        this.region = clientCache.getRegion("exampleRegion");
    }

    public void performPuts() {
        CompletableFuture<Void> puts = CompletableFuture.runAsync(() -> {
            IntStream.range(0, 100000).parallel().forEach(i -> {
                region.put("key" + i, "value" + i);
            });
        });
        puts.join();
    }

    public void performReads() {
        CompletableFuture<Void> reads = CompletableFuture.runAsync(() -> {
            IntStream.range(0, 10000).parallel().forEach(i -> {
                region.get("key" + i);
            });
        });
        reads.join();
    }
}