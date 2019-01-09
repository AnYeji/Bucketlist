package com.rsupport.bucketlist.core.util;

import com.rsupport.bucketlist.core.config.CacheConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class JwtUtils {

  @Autowired
  @Qualifier(CacheConfig.EHCACHE_MANAGER_NAME)
  private CacheManager cacheManager;

  public String createAccessToken(String email) {
    String secret = "secret";
    int expireSeconds = 100;

    String token = Jwts.builder()
            .setSubject(email)
            .signWith(SignatureAlgorithm.HS256, secret)
            .setExpiration(new Date())
            .compact();

    return token;
  }

  public boolean isValidAccessToken(String token, String email) {
    String secret = "secret";
    boolean valid = false;
    try {
      Jwts.parser().requireSubject(email).setSigningKey(secret).parseClaimsJws(token);
      valid = true;
    } catch (Exception e) {
      log.warn(String.format("jwt invalid [token : %s] [error : %s]", token, e.getMessage()));
    }
    return valid;
  }

  public String createRefreshToken(String accessToken) {
    String refreshToken = UUID.randomUUID().toString();
    Cache cache = cacheManager.getCache(CacheConfig.REFRESH_TOKEN);
    cache.put(refreshToken, accessToken);
    return refreshToken;
  }
}
