package com.rsupport.bucketlist.core.util;

import com.rsupport.bucketlist.core.config.CacheConfig;
import com.rsupport.bucketlist.core.exception.ExpiredTokenException;
import com.rsupport.bucketlist.core.exception.InvalidTokenException;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class JwtUtils {

  @Autowired
  @Qualifier(CacheConfig.EHCACHE_MANAGER_NAME)
  private CacheManager cacheManager;

  public String createAccessToken(String userId) {
    String secret = "mybury";
    int expireSeconds = 60 * 60;

    String token = Jwts.builder()
            .setSubject(userId)
            .signWith(SignatureAlgorithm.HS256, secret)
            .setExpiration(DateUtils.addSeconds(DateUtil.getDate(), expireSeconds))
            .compact();

    return token;
  }

  public void isValidAccessToken(String token, String userId) {
    String secret = "mybury";
    try {
      Jwts.parser().requireSubject(userId).setSigningKey(secret).parseClaimsJws(token);
    } catch (ExpiredJwtException e) {
      log.warn(String.format("jwt expired [token : %s] [error : %s]", token, e.getMessage()));
      throw new ExpiredTokenException("expired token");
    } catch (JwtException e) {
      log.warn(String.format("jwt invalid [token : %s] [error : %s]", token, e.getMessage()));
      throw new InvalidTokenException("invalid token");
    }
  }

  public String createRefreshToken(String accessToken) {
    String refreshToken = UUID.randomUUID().toString();
    Cache cache = cacheManager.getCache(CacheConfig.REFRESH_TOKEN);
    cache.put(refreshToken, accessToken);
    return refreshToken;
  }
}
