package caching.example;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.annotation.EnableCaching;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

@Configuration
@EnableCaching
public class EhcacheConfig {

    @Bean
    public CacheManager cacheManager() {
        org.ehcache.config.CacheConfiguration<Object, Object> ehCacheConfig =
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        Object.class, Object.class,
                        ResourcePoolsBuilder.heap(100)
                ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(5)))
                .build();

        javax.cache.configuration.Configuration<Object, Object> jcacheConfig =
                Eh107Configuration.fromEhcacheCacheConfiguration(ehCacheConfig);

        CachingProvider provider = Caching.getCachingProvider();
        javax.cache.CacheManager jCacheManager = provider.getCacheManager();
        jCacheManager.createCache("myCache", jcacheConfig);

        return new JCacheCacheManager(jCacheManager); // Spring wraps it
    }
}
