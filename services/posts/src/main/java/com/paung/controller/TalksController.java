package com.paung.controller;

import cn.hutool.core.lang.Snowflake;
import com.paung.entity.Talks;
import com.paung.service.TalksService;
import com.paung.talks.ReplyTalksItemResponse;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class TalksController {

  private final TalksService talksService;


//  @PostMapping
//  public Talks createTalk(@RequestBody Talks talks, HttpServletRequest request) {
//    String token = request.getHeader("Authorization");
//    if (token != null && token.startsWith("Bearer ")) {
//      token = token.substring(7); // remove "Bearer " prefix
//      String username = jwtService.extractUsername(token);
//    }
//    // ...
//  }
//


  @PostMapping
  public ResponseEntity<List> createTalk(@RequestParam("user_id") String userId,
                                   @RequestParam("content") String content,
                                   @RequestParam(value = "parent_talk_id", required = false) String parentTalkId,
                                   @RequestParam("files") MultipartFile files){
    List<String> fileNames = new ArrayList<>();

    Talks parentTalkIdReq = new Talks();
    parentTalkIdReq.setTalk_id(parentTalkId);

    Talks talks = new Talks();
    talks.setUser_id(userId);
    talks.setParentTalk(parentTalkIdReq);
    talks.setContent(content);
    var user_id = talks.getUser_id();
    var parent_talk_id = talks.getParentTalk();

    Arrays.asList(files).stream().forEach(file -> {
      fileNames.add(file.getOriginalFilename());
    });

    return ResponseEntity.ok(fileNames);
//    if(user_id != null && parent_talk_id != null){
//      return talksService.createTalkerForParentTalkId(talks.getParentTalk(), talks.getUser_id(), talks.getContent());
//    }else{
//      return talksService.createTalks(talks);
//    }
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
