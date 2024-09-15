package com.paung.service;

import cn.hutool.core.lang.Snowflake;
import com.paung.entity.Like;
import com.paung.entity.Talks;
import com.paung.repository.TalksRepository;
import com.paung.talks.ReplyTalksItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TalksService {

  private final TalksRepository talksRepository;

  public Talks createTalkerForParentTalkId(Talks parentTalk, String user_id, String content){
//    UUID uuid = UUID.randomUUID();
//    Talks parentTalk = new Talks();
////    parentTalk.setTalk_id(snowflake.nextIdStr());
//    parentTalk.setTalk_id("1827356862700937216");

    Talks talks = new Talks();
    talks.setContent(content);
    talks.setUser_id(user_id);
    talks.setParentTalk(parentTalk);
    talksRepository.save(talks);
    return talks;
  }

  public Talks createTalks(Talks talks){
    return talksRepository.save(talks);
  }


  public List<ReplyTalksItemResponse> getReplies() {
    List<Talks> replies = talksRepository.replyTalkItem();
    List<ReplyTalksItemResponse> responses = new ArrayList<>();

    for (Talks reply : replies) {
      ReplyTalksItemResponse response = new ReplyTalksItemResponse();
      response.setTalksType("reply");
      response.setCreatedAt(reply.getCreated_at());
      response.setContent(reply.getContent());
      response.setUserId(reply.getUser_id());

      if (reply.getParentTalk() != null) {
        ReplyTalksItemResponse parentResponse = new ReplyTalksItemResponse();
        parentResponse.setTalksType("reply");
        parentResponse.setCreatedAt(reply.getParentTalk().getCreated_at());
        parentResponse.setContent(reply.getParentTalk().getContent());
        parentResponse.setUserId(reply.getParentTalk().getUser_id());
        response.setReplyItem(parentResponse);
      }

      responses.add(response);
    }

    return responses;
  }




//  public Talks updateTalk(String talkId, Talks updatedTalk) {
//    Talks existingTalk = talksRepository.findById(talkId).orElseThrow();
//    existingTalk.setContent(updatedTalk.getContent());
//    existingTalk.setUser_id(updatedTalk.getUser_id());
//    existingTalk.setParentTalk(updatedTalk.getParentTalk());
//    return talksRepository.save(existingTalk);
//  }
//
//  public Talks updateTalkContent(String talkId, String newContent) {
//    Talks existingTalk = talksRepository.findById(talkId).orElseThrow();
//    existingTalk.setContent(newContent);
//    return talksRepository.save(existingTalk);
//  }
//
//  public Talks updateTalkParent(String talkId, Talks newParentTalk) {
//    Talks existingTalk = talksRepository.findById(talkId).orElseThrow();
//    existingTalk.setParentTalk(newParentTalk);
//    return talksRepository.save(existingTalk);
//  }
//
//  public Talks updateTalkUserId(String talkId, UUID newUserId) {
//    Talks existingTalk = talksRepository.findById(talkId).orElseThrow();
//    existingTalk.setUser_id(newUserId);
//    return talksRepository.save(existingTalk);
//  }
//
//  public void deleteTalk(String talkId) {
//    Talks existingTalk = talksRepository.findById(talkId).orElseThrow();
//    existingTalk.setDeleted_at(new Timestamp(System.currentTimeMillis()));
//    talksRepository.save(existingTalk);
//  }
//
//
}
