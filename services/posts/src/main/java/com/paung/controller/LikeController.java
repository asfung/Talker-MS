package com.paung.controller;


import com.paung.entity.Like;
import com.paung.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/like")
@RequiredArgsConstructor
public class LikeController {

  @Autowired
  private LikeService likeService;

  @PostMapping
  public Like createLike(@RequestBody Like like){
    return likeService.createLike(like);
  }

  @GetMapping("/{userId}")
  public List<Like> talksLiked(@PathVariable String userId){
    return likeService.likedListByUserId(userId);
  }



}

