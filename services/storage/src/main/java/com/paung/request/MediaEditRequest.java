package com.paung.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MediaEditRequest {
  private List<String> ids;
  private String talkId;
}
