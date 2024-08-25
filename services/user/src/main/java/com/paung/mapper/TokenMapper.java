package com.paung.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenMapper {

  @JsonProperty("accessToken")
  private String token;

  public TokenMapper(String token) {
    this.token = token;
  }
  public String getToken() {
    return token;
  }
}
