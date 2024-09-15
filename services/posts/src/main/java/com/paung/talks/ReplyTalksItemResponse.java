package com.paung.talks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyTalksItemResponse {
  private String talksType;
  private Timestamp createdAt;
  private String content;
  private String userId;
  private ReplyTalksItemResponse replyItem;
}
