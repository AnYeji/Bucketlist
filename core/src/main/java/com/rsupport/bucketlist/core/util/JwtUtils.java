package com.rsupport.bucketlist.core.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

  public String createAccessToken(String email) {
    String secret = "secret";
    int expireSeconds = 100;

    String token = Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS256, secret).setExpiration(new Date()).compact();

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
    return "";
  }
}
