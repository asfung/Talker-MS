package com.paung.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paung.utils.ULIDGenerated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long userprofile_id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  @JsonIgnore
//  @JsonManagedReference // optional
  private User user;
  private String avatar;
  private String bio;

  private String media_id;

//  private String media_id;

}

