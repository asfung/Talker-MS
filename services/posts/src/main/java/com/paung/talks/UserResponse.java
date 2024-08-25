package com.paung.talks;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
  private String user_id;
  private String username;
  private String email;
  private String password;
}
