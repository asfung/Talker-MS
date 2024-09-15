package com.paung.controller;

import cn.hutool.core.lang.Snowflake;
import com.paung.entity.Talks;
import com.paung.service.TalksService;
import com.paung.talks.ReplyTalksItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class TalksController {

  private final TalksService talksService;



  @PostMapping
  public Talks createTalk(@RequestBody Talks talks){
    var user_id = talks.getUser_id();
    var parent_talk_id = talks.getParentTalk();
    if(user_id != null && parent_talk_id != null){
      return talksService.createTalkerForParentTalkId(talks.getParentTalk(), talks.getUser_id(), talks.getContent());
    }else{
      return talksService.createTalks(talks);
    }
  }

  @GetMapping("/test")
  public String heloWorld(){
    return "<h1>Hello World</h1>";
  }

  @GetMapping("/generator")
  public String idGenerator(){
    Snowflake snowflake = new Snowflake(1, 2);
    return snowflake.nextIdStr();
  }

  @GetMapping("/replies")
  public List<ReplyTalksItemResponse> getReplies() {
    return talksService.getReplies();
  }

}
