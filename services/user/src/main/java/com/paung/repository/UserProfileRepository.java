package com.paung.repository;

import com.paung.dto.UserProfileDTO;
import com.paung.entity.User;
import com.paung.entity.UserProfile;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
  Optional<UserProfile> findByUser(User user);

  @Query(name = "getUserProfileByUserId", nativeQuery = true)
  UserProfileDTO getAccountByUserId(@Param("userId") String userId);

  @Query(name = "getUserProfileByUsername", nativeQuery = true)
  UserProfileDTO getAccountByUsername(@Param("username") String userId);

  @Override
  Optional<UserProfile> findById(Long aLong);
}
