package com.paung.repository;

import com.paung.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {


//  @Query(
//          value = "select * from talks t left join like l on t. = up.user_id where u.user_id = ':userId'",
//          nativeQuery = true
//  )
//  Like getLikeByPostId(@Param("userId") String userId);


//  basically like is not has relation between the talks just like it self and then the seelct query just grab the content and media_id
  @Query(value = "select l.*, t.content from talks t left join likes l on t.user_id = l.user_id where l.user_id = ':userId'", nativeQuery = true)
  List<Like> findByUser_id(String userId);



}
