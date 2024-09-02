package com.paung.entity;

import com.paung.entity.listener.UserProfileListener;
import com.paung.utils.ULIDGenerated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @ULIDGenerated
  private String user_id;
  @Column(unique = true)
  private String username;
  private String email;
  private String password;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private UserProfile userProfile;

  public User() {
    this.userProfile = new UserProfile();
    this.userProfile.setUser(this);
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.userProfile = new UserProfile();
    this.userProfile.setUser(this);
  }


}
