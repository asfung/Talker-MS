package com.paung.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {
  private Long userprofile_id;
  private String user_id;
  private String email;
  private String username;
  private String avatar;
  private String bio;
  private String display_name;
  private String media_id;

  public UserProfileDTO(Long userprofile_id, String user_id, String email, String username, String avatar, String bio, String display_name, String media_id) {
    this.userprofile_id = userprofile_id;
    this.user_id = user_id;
    this.email = email;
    this.username = username;
    this.avatar = avatar;
    this.bio = bio;
    this.display_name = display_name;
    this.media_id = media_id;
  }


}
