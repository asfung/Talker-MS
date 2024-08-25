package com.paung.repository;

import com.paung.entity.User;
import com.paung.entity.UserProfile;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findById(String userId);
//  Optional<User> findByName(String name);
  Optional<User> findByUsername(String username);

}
