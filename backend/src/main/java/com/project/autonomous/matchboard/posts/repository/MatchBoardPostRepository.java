package com.project.autonomous.matchboard.posts.repository;

import com.project.autonomous.matchboard.posts.entity.MatchBoardPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchBoardPostRepository extends JpaRepository<MatchBoardPost, Long> {

}