package com.project.autonomous.user.repository;

import com.project.autonomous.user.entity.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
    Optional<ArrayList<UserInterest>> findAllByUserInterestIdUserId(long userId);
}