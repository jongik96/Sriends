package com.project.autonomous.team.repository;

import com.project.autonomous.team.entity.TeamBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface TeamBoardCommentRepository extends JpaRepository<TeamBoardComment, Long> {
//    List<TeamBoardComment> findAllByTeamBoardId(long teamBoardId);
//    List<TeamBoardComment> findAllByParentIdAAndTeamBoardId(long parentId, long teamBoardId);
}