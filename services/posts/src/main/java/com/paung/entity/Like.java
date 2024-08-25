package com.paung.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes")
//Like: like_id, post_id, user_id, created_at
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID like_id;

  @ManyToOne
  @JoinColumn(name = "talk_id", nullable = false, referencedColumnName = "talk_id")
  private Talks talks;

  private UUID user_id;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private Timestamp created_at;

  @Column(nullable = false, updatable = true)
  @CreationTimestamp
  private Timestamp updated_at;

  @Column
  private Timestamp deleted_at;

//  private LocalDateTime created_at;
//  private LocalDateTime updated_at;
//
//  @Column(nullable = true)
//  private LocalDateTime deleted_at;


}
