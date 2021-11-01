package com.project.autonomous.user.repository;

import com.project.autonomous.user.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    Optional<ArrayList<Interest>> findAllByUserId(long userId);
}