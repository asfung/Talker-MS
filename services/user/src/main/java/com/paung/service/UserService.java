package com.paung.service;

import com.paung.dto.UserProfileDTO;
import com.paung.entity.User;
import com.paung.entity.UserProfile;
import com.paung.exception.UserNotFoundException;
import com.paung.exception.UserProfileNotFoundException;
import com.paung.repository.UserProfileRepository;
import com.paung.repository.UserRepository;
import com.paung.response.AccountProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserProfileRepository userProfileRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtService jwtService;

  public User saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
//    return "user added to table";
    return user;
  }

  public AccountProfileResponse getAccount(String userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found with id : " + userId));
    UserProfile userProfile = userProfileRepository.findByUser(user).orElseThrow(() -> new UserProfileNotFoundException("UserProfile not found with id : " + userId));
    return new AccountProfileResponse(user, userProfile);
  }

//  public UserProfileDTO fullUserData(String userId){
//    return userProfileRepository.getAccountByUserId(userId);
//  }
  public ResponseEntity<UserProfileDTO> fullUserData(String username){
    UserProfileDTO userProfile = userProfileRepository.getAccountByUsername(username);
    if(userProfile == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(userProfile);
  }


  public Optional<UserProfile> findById(Long userProfileId){
    return userProfileRepository.findById(userProfileId);
  }

  public String generateToken(String username) {
    return jwtService.generateToken(username);
  }

  public void validateToken(String token) {
    jwtService.validateToken(token);
  }


}