package com.paung.response;

import com.paung.entity.Media;
import com.paung.entity.PhotoProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoProfileResponse {
  private PhotoProfile photoProfile;
  private String originalFileName;
  private String generated_name;
}
