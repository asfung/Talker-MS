package com.paung.repository;

import com.paung.entity.PhotoProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoProfileRepository extends MongoRepository<PhotoProfile, String> {
}
