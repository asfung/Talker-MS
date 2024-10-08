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
@NamedNativeQuery(
        name = "getTalkById",
        query = "SELECT * FROM talks t where t.talk_id = :talkId",
        resultSetMapping = "Talks"
)
@SqlResultSetMapping(
        name = "Talks",
        classes = @ConstructorResult(
                targetClass = Talks.class,
                columns = {
                        @ColumnResult(name = "talk_id"),
                        @ColumnResult(name = "user_id"),
                        @ColumnResult(name = "content"),
                        @ColumnResult(name = "media_id"),
                        @ColumnResult(name = "created_at"),
                        @ColumnResult(name = "updated_at"),
                        @ColumnResult(name = "deleted_at"),
//                        @ColumnResult(name = "parent_talk_id"),
//                        @ColumnResult(name = "like_id")
                }
        )
)
public class Talks {

  @Id
  @SNFID
  private String talk_id;

  //  @ManyToOne
//  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//  private User user;
  private String user_id;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @Column(nullable = true)
  private String media_id;

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