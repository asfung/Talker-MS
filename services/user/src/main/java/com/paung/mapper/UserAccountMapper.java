package com.paung.mapper;

import com.paung.entity.User;
import com.paung.entity.UserProfile;
import com.paung.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAccountMapper {
  private UserRepository userRepository;

  public void toUserAccountResponse(User user){
  }

}
