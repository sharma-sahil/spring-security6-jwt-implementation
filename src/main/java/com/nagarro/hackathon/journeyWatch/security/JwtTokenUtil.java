package com.nagarro.hackathon.journeyWatch.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil implements Serializable {

  private static final long serialVersionUID = -2550185165626007488L;

  public static final String SECRET = "VGhpc0lzQUxvbmdTZWNyZXRLZXlGb3JKV1RHZW5lcmF0aW9uSXRIYXNUb0JlTWluaW11bVNpeHR5Rm91ckNoYXJhY3RlcnM=";
  public static final String LAST_NAME = "last_name";

  public static final String FIRST_NAME = "first_name";

  @Value("${jwt.acesss-token.expiry-minutes}")
  private long jwtTokenValidity;

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  public Claims getAllClaimsFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = generateClaims(userDetails);

    return doGenerateToken(claims, userDetails.getUsername());
  }

  private String doGenerateToken(Map<String, Object> claims, String subject) {
    return Jwts.builder().setClaims(claims).setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity * 60 * 1000))
        .signWith(getSignKey(),
            SignatureAlgorithm.HS512).compact();
  }

  private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }


  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private Map<String, Object> generateClaims(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    UserPrincipal principal = (UserPrincipal) userDetails;
    claims.put(FIRST_NAME, principal.getFirstName());
    claims.put(LAST_NAME, principal.getLastName());
    return claims;
  }

}
