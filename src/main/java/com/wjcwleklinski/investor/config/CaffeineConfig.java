package com.wjcwleklinski.investor.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {

    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("investments");
        cacheManager.setCaffeine(caffeineBuilder());
        return cacheManager;
    }

    Caffeine< Object, Object > caffeineBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(300)
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .weakKeys()
                .recordStats();
    }
}
