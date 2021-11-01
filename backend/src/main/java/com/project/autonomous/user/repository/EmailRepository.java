package com.project.autonomous.user.repository;

import com.project.autonomous.user.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
//    Optional<Email> findByUserId(long userId);
    Optional<Email> findByEmail(String email);
}
