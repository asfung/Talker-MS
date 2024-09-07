package com.paung.service;

import com.paung.entity.User;
import com.paung.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtService {
  @Autowired
  private UserRepository userRepository;


  public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


  public void validateToken(final String token) {
    Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
  }


  public String generateToken(String userName) {
    Map<String, Object> claims = new HashMap<>();
    Optional<User> user = userRepository.findByUsername(userName);
//    return createToken(claims, userName);
//    return createTokenUser(claims, user);
    if(user.isPresent()){
//      System.out.println("user = " + user.get());
      return createTokenUser(claims, user);
    }else{
      throw new RuntimeException("User not found");
    }
  }

  private String createToken(Map<String, Object> claims, String userName) {
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(new Date(System.currentTimeMillis()))
//            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
            .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
  }

  private String createTokenUser(Map<String, Object> claims, Optional<User> user) {
    if(user.isPresent()){
      User currentUser = user.get();
      claims.put("id", currentUser.getUser_id());
      claims.put("username", currentUser.getUsername());
      claims.put("email", currentUser.getEmail());
      claims.put("password", currentUser.getPassword());
      claims.put("user_profile", List.of(currentUser.getUserProfile()));

      return Jwts.builder()
              .setClaims(claims)
//              .setSubject(String.valueOf(user))
              .setSubject(currentUser.getUsername())
              .setIssuedAt(new Date(System.currentTimeMillis()))
  //            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
              .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }else{
      throw new RuntimeException("User not found");
    }
  }

  private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }

//  tambahan func
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
//    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    return (username.equals(userDetails.getUsername()));
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
            .parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }
}