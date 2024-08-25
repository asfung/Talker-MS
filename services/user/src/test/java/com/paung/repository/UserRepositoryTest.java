package com.paung.repository;

import com.paung.entity.User;
import com.paung.entity.UserProfile;
import com.paung.response.AccountProfileResponse;
import com.paung.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserService userService;

  @Autowired
  private UserProfileRepository userProfileRepository;

  @Test
  void saveUser(){
//    User user = new User();
//    user.setUsername("paung");
//    user.setEmail("paung@gmail.com");
//    user.setPassword("paung");
//    userRepository.save(user);
    User user = new User("paung", "paung@gmail.com", "paung");
    userRepository.save(user);

  }

  @Test
  void saveUserProfile(){
    String user_id = "01J63RSSY4Y96261D5TWHHB191";
    User user = (User) userRepository.findById(user_id).orElseThrow();

    UserProfile userProfile = new UserProfile();
    userProfile.setUser(user);
    userProfile.setBio("helo world");
    userProfile.setMedia_id("N1N1N1N1N1N1");

    userProfileRepository.save(userProfile);
  }

  @Test
  void getAccount(){
    AccountProfileResponse accountProfileResponse = userService.getAccount("32c08805-bd32-4fdd-bd93-5f12e22a52e3");
    System.out.println("accountProfileResponse = " + accountProfileResponse);
  }

}
