package com.paung.service;

import com.paung.entity.Like;
import com.paung.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

  @Autowired
  private LikeRepository likeRepository;


  public Like createLike(Like like){
    return likeRepository.save(like);
  }

  public List<Like> likedListByUserId(String userId){
    return likeRepository.findByUser_id(userId);
  }





}
