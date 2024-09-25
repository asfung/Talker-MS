package com.paung.dto;

import com.paung.entity.Like;
import com.paung.entity.Talks;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TalkResponse {

  private String talk_id;
  private String user_id;
  private String content;
  private String media_id;
  private Timestamp created_at;
  private Timestamp updated_at;
  private Timestamp deleted_at;
  private Talks parentTalk;
  List<Like> likes = new ArrayList<>();


}
