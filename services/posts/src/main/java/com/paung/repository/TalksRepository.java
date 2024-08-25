package com.paung.repository;

import com.paung.entity.Talks;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TalksRepository extends JpaRepository<Talks, String> {

  @Query(
    value = "SELECT * FROM talks t where t.parent_talk_id = :parentTalkId",
    nativeQuery = true
  )
  Talks getTalkByParentTalkerId(@Param("parentTalkId") String parentTalkId);

  @Query(
    value = "insert into talks (talk_id, user_id, content, parent_talk_id) values (:talkId, :userId, :content, :parentTalkId)",
    nativeQuery = true
  )
  Talks insertReplyTalk(@Param("talkId") String ulid, @Param("userId")UUID userId, @Param("content") String content, @Param("parentTalkId") String parentTalkId);

  @Query("SELECT t FROM Talks t WHERE t.parentTalk IS NOT NULL")
  List<Talks> replyTalkItem();

//  Talks findById(String talkId);
}
