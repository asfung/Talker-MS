package com.paung.repository;

import com.paung.entity.PhotoProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoProfileRepository extends MongoRepository<PhotoProfile, String> {


  @Query(
          "{ user_id: ?0 }"
  )
  Optional<PhotoProfile> findFirstByUser_id(String user_id);
}
