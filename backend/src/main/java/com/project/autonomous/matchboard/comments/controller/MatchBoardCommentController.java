package com.project.autonomous.matchboard.comments.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MatchBoardComment", description = "매치 게시글 댓글 API")
@RestController
@RequestMapping("/match/board/comments")
@RequiredArgsConstructor
public class MatchBoardCommentController {

}
