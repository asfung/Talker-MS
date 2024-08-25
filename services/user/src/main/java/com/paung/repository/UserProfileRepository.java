package com.paung.repository;

import com.paung.entity.User;
import com.paung.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
  Optional<UserProfile> findByUser(User user);

  @Query(
          value = "select * from user u left join user_profile up on u.user_id = up.user_id where u.user_id = ':userId'",
          nativeQuery = true
  )
  UserProfile getAccount(@Param("userId") String userId);

  @Override
  Optional<UserProfile> findById(Long aLong);
}
