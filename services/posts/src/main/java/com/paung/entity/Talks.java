package com.paung.entity;

import com.paung.utils.SNFID;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Talks {

  @Id
  @SNFID
  private String talk_id;

  //  @ManyToOne
//  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//  private User user;
  private UUID user_id;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private Timestamp created_at;

  @Column(nullable = false, updatable = true)
  @CreationTimestamp
  private Timestamp updated_at;

  @Column
  private Timestamp deleted_at;

  @ManyToOne
  @JoinColumn(name = "parent_talk_id", referencedColumnName = "talk_id")
  private Talks parentTalk;

  @OneToMany(mappedBy = "like_id", cascade = CascadeType.ALL)
  List<Like> likes = new ArrayList<>();

}