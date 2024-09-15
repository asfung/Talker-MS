package com.paung.response;

import com.paung.entity.Media;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaResponse {
  private Media media;
  private String originalFileName;
  private String generated_name;

}
