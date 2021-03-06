package edu.security.second.repository;

import edu.security.second.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByName(String name);

  Optional<User> findByEmail(String email);

  Boolean deleteUserByEmail(String email);
}
