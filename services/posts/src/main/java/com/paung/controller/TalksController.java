package com.paung.controller;

import cn.hutool.core.lang.Snowflake;
import com.paung.service.TalksService;
import com.paung.talks.ReplyTalksItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class TalksController {

  private final TalksService talksService;

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
