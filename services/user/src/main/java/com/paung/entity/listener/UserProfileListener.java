package com.paung.entity.listener;

import com.paung.entity.User;
import com.paung.entity.UserProfile;
import com.paung.repository.UserProfileRepository;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserProfileListener {

  @Autowired
  private UserProfileRepository userProfileRepository;

  @PostPersist
  @Transactional
  public void postPersist(User user) {
    UserProfile userProfile = new UserProfile();
    userProfile.setUser(user);
    userProfileRepository.save(userProfile);
  }
}
