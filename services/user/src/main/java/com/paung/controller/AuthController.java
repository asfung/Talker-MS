package com.paung.controller;

import com.paung.dto.AuthRequest;
import com.paung.entity.User;
import com.paung.mapper.TokenMapper;
import com.paung.response.AccountProfileResponse;
import com.paung.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  @Autowired
  private UserService service;

  @Autowired
  private AuthenticationManager authenticationManager;

  @GetMapping
  public String index(){
    return "Helo Index Auth";
  }

  @PostMapping("/register")
  public User addNewUser(@RequestBody User user) {
    return service.saveUser(user);
//    return ResponseEntity.ok(user);
  }

  @PostMapping("/token")
  public TokenMapper getToken(@RequestBody AuthRequest authRequest) {
    Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    if (authenticate.isAuthenticated()) {
      String token =  service.generateToken(authRequest.getUsername());
      return new TokenMapper(token);
    } else {
      throw new RuntimeException("invalid access");
    }
  }

  @GetMapping("/validate")
  public String validateToken(@RequestParam("token") String token) {
    service.validateToken(token);
    return "Token is valid";
  }
}
