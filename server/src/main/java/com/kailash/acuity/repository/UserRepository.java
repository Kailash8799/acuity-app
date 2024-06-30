package com.kailash.acuity.repository;

import com.kailash.acuity.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByUsernameOrEmail(String username, String email);
  Optional<User> findByEmail(String email);
}
