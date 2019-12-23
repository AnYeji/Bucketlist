package com.rsupport.bucketlist.core.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.Nullable;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {

  public static final String EHCACHE_MANAGER_NAME = "ehCacheSpringManager";

  public static final String REFRESH_TOKEN = "refreshToken";

  @Bean(name = EHCACHE_MANAGER_NAME)
  public CacheManager cacheManager() {
    return new EhCacheCacheManager(ehCacheManager().getObject());
  }

  @Override
  public CacheResolver cacheResolver() {
    return new SimpleCacheResolver();
  }

  @Override
  public KeyGenerator keyGenerator() {
    return new SimpleKeyGenerator();
  }

  @Override
  public CacheErrorHandler errorHandler() {
    return new SimpleCacheErrorHandler();
  }

  @Bean
  public EhCacheManagerFactoryBean ehCacheManager() {
    EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
    cmfb.setConfigLocation(new ClassPathResource("ehCache.xml"));
    return cmfb;
  }
}
