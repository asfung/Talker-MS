package com.paung.response;

import com.paung.entity.User;
import com.paung.entity.UserProfile;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountProfileResponse {
  private String userId;
  private String username;
  private String email;
  private String avatar;
  private String bio;
  private String mediaId;

  public AccountProfileResponse(User user, UserProfile userProfile) {
    this.userId = user.getUser_id();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.avatar = userProfile.getAvatar();
    this.bio = userProfile.getBio();
    this.mediaId = userProfile.getMedia_id();
  }

}
