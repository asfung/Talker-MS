package com.paung.controller;

import com.paung.entity.User;
import com.paung.entity.UserProfile;
import com.paung.response.AccountProfileResponse;
import com.paung.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
  @Autowired
  private UserService userService;

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


}
