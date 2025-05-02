package caching.example;

import org.ehcache.config.builders.*;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.units.MemoryUnit;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.ehcache.EhCacheCacheManager;

@Configuration
@EnableCaching
public class NativeEhcacheConfig {

    @Bean
    public org.springframework.cache.CacheManager cacheManager() {
        CacheManager ehcacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("myCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class,
                                String.class,
                                ResourcePoolsBuilder.heap(100)
                        ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(5)))
                ).build(true);

        return new EhCacheCacheManager(ehcacheManager); // Spring native cache manager
    }
}
