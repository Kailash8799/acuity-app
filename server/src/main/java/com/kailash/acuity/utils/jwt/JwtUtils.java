package com.kailash.acuity.utils.jwt;

import com.kailash.acuity.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  @Value("${spring.app.jwtSecret}")
  private String jwtSecret;

  @Value("${spring.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String getJwtFromHeader(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public String getUsernameFromToken(String token) {
    return Jwts
      .parser()
      .verifyWith((SecretKey) key())
      .build()
      .parseSignedClaims(token)
      .getPayload()
      .getSubject();
  }

  public Claims getClaimsFromToken(String token) {
    return Jwts
      .parser()
      .verifyWith((SecretKey) key())
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }

  public String generateTokenFromUser(User user) {
    String username = user.getUsername();
    Map<String, Object> claims = new HashMap<>();
    claims.put("username", username);
    claims.put("email", user.getEmail());
    // claims.put("roles", user.getAuthorities());
    return Jwts
      .builder()
      .claims(claims)
      .subject(username)
      .issuedAt(new Date())
      .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
      .signWith(key())
      .compact();
  }

  public Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public boolean validateJwtToken(String authToken) {
    Jwts
      .parser()
      .verifyWith((SecretKey) key())
      .build()
      .parseSignedClaims(authToken);
    return true;
  }
}
