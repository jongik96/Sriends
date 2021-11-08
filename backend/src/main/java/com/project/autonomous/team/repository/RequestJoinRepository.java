package com.project.autonomous.team.repository;

import com.project.autonomous.team.entity.RequestJoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface RequestJoinRepository extends JpaRepository<RequestJoin, Long> {
    ArrayList<RequestJoin> findAllByTeamId(long teamId);

    Optional<RequestJoin> findByUserIdAndTeamId(long userId, long teamId);
}