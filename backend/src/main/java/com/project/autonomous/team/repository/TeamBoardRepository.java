package com.project.autonomous.team.repository;

import com.project.autonomous.team.entity.TeamBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamBoardRepository extends JpaRepository<TeamBoard, Long> {
    List<TeamBoard> findAllByTeamId(long teamId);
}