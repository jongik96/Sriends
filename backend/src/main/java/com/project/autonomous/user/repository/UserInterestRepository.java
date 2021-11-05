package com.project.autonomous.user.repository;

import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.entity.UserInterest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
    Optional<ArrayList<UserInterest>> findAllByUserInterestIdUserId(long userId);

    List<UserInterest> findAllByUserInterestIdUser(User user);
}