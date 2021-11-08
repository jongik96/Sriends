package com.project.autonomous.matchboard.comments.repository;

import com.project.autonomous.matchboard.comments.entity.MatchBoardComment;
import com.project.autonomous.matchboard.posts.entity.MatchBoardPost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchBoardCommentRepository extends JpaRepository<MatchBoardComment, Long> {

    // 댓글 조회 (부모 id 오름차순, 생성일자 오름차순)
    @Query("select mbc from MatchBoardComment mbc where mbc.matchBoardPost = :matchBoardPost order by mbc.parentId, mbc.createdAt")
    List<MatchBoardComment> findAllComment(@Param("matchBoardPost") MatchBoardPost matchBoardPost);
}