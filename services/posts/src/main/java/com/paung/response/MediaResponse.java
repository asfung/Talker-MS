package com.paung.response;

import com.paung.dto.MediaDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MediaResponse {
  private MediaDTO media;
  private String originalFileName;
  private String generated_name;

}

