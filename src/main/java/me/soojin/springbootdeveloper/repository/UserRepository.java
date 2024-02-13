package me.soojin.springbootdeveloper.repository;

import me.soojin.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
