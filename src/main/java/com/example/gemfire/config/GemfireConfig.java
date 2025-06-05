package com.example.gemfire.config;

import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.cache.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GemfireConfig {

    @Bean
    public ClientCache clientCache() {
        return new ClientCacheFactory()
                .addPoolLocator("localhost", 10334)
                .create();
    }

    @Bean
    public Region<Object, Object> exampleRegion(ClientCache clientCache) {
        return clientCache.createClientRegionFactory(ClientRegionShortcut.PROXY)
                .create("exampleRegion");
    }
}