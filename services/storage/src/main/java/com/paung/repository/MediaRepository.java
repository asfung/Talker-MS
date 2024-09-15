package com.paung.repository;

import com.paung.entity.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends MongoRepository<Media, String> {

  /*
  * LESSON FOR MONGO DATA Persistance:
  * if the entity variable is post_id then the repository must be findFirstBy or findTopBy + post_id will be Post_id
  * if the entity variable is postId then the repository must be findFirstBy or findTopBy + post_id will be PostId
  *
  *
  *  */

  @Query(
          "{ media_id: ?0 }"
  )
  Media findFirstByMedia_id(String media_id);



//  @Query(
//          "post_id: ?0"
//  )
//  @Query("SELECT m FROM media m WHERE m.postId = ?1")
//  List<Media> findMediaByPostId(String post_id);

  @Query("{ post_id: ?0 }")
  List<Media> findByPost_id(String postId);

}
