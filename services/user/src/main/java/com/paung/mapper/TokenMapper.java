package com.paung.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenMapper {

  private String user_id;
  private String username;
  @JsonProperty("accessToken")
  private String token;
  private String success = "true";

  public TokenMapper() {
  }

  public TokenMapper(String token) {
    this.token = token;
  }

  public TokenMapper(String token, String user_id, String username) {
    this.user_id = user_id;
    this.username = username;
    this.token = token;
  }
  public String getToken() {
    return token;
  }
}
