package com.paung.controller;

import com.paung.config.CustomUserDetailsService;
import com.paung.entity.User;
import com.paung.entity.UserProfile;
import com.paung.repository.UserRepository;
import com.paung.response.AccountProfileResponse;
import com.paung.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @GetMapping
  String Index(){
    return "<h2>Index User Endpoint</h2>";
  }

  @GetMapping("/{userId}")
  ResponseEntity<AccountProfileResponse> getAccount(@PathVariable("userId") String userId){
    return ResponseEntity.ok(userService.getAccount(userId));
//    service.getAccount(userId);
  }

  @GetMapping("/user-profile/{userId}")
  Optional<UserProfile> getUserProfile(@PathVariable("userId") Long userId){
    return userService.findById(userId);
  }

  @GetMapping("/profile/{userId}")
  UserProfile fullUserData(@PathVariable("userId") String userId){
    return userService.fullUserData(userId);
  }

  @GetMapping("/all")
  List<User> allUser(){
    return userRepository.findAll();
  }

  @GetMapping("/info/{username}")
  UserDetails userInfo(@PathVariable("username") String username){
    return customUserDetailsService.loadUserByUsername(username);
  }


}
