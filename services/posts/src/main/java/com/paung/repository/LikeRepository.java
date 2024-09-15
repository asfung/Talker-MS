package com.paung.repository;

import com.paung.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {


  @Query(
          value = "select * from talks t left join like l on t. = up.user_id where u.user_id = ':userId'",
          nativeQuery = true
  )
  Like getLikeByPostId(@Param("userId") String userId);

}
