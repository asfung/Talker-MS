package com.paung.entity;

import com.paung.entity.listener.UserProfileListener;
import com.paung.utils.ULIDGenerated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
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


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
