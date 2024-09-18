package com.paung.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paung.dto.UserProfileDTO;
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
@NamedNativeQuery(
        name = "getUserProfileByUserId",
        query = "select up.userprofile_id as userprofile_id, up.user_id as user_id, u.email as email, u.username as username, up.avatar as avatar, up.bio as bio, up.display_name as display_name, up.media_id as media_id from user u left join user_profile up on u.user_id = up.user_id where u.user_id = :userId",
        resultSetMapping = "UserProfileDTO"
)
@NamedNativeQuery(
        name = "getUserProfileByUsername",
        query = "select up.userprofile_id as userprofile_id, up.user_id as user_id, u.email as email, u.username as username, up.avatar as avatar, up.bio as bio, up.display_name as display_name, up.media_id as media_id from user u left join user_profile up on u.user_id = up.user_id where u.username = :username",
        resultSetMapping = "UserProfileDTO"
)
@SqlResultSetMapping(
        name = "UserProfileDTO",
        classes = @ConstructorResult(
                targetClass = UserProfileDTO.class,
                columns = {
                        @ColumnResult(name = "userprofile_id"),
                        @ColumnResult(name = "user_id"),
                        @ColumnResult(name = "email"),
                        @ColumnResult(name = "username"),
                        @ColumnResult(name = "avatar"),
                        @ColumnResult(name = "bio"),
                        @ColumnResult(name = "display_name"),
                        @ColumnResult(name = "media_id")
                }
        )
)
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
  private String display_name;

  private String media_id;

//  private String media_id;

}

