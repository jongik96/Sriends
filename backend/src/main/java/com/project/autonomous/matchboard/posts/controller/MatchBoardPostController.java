package com.project.autonomous.matchboard.posts.controller;

import com.project.autonomous.common.exception.ErrorResponse;
import com.project.autonomous.matchboard.posts.dto.request.MatchBoardCreateReq;
import com.project.autonomous.matchboard.posts.dto.request.MatchBoardUpdateReq;
import com.project.autonomous.matchboard.posts.dto.response.MatchBoardPostInfoRes;
import com.project.autonomous.matchboard.posts.service.MatchBoardPostServiceImpl;
import com.project.autonomous.user.dto.response.UserTeamListRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MatchBoardPost", description = "매치 게시글 API")
@RestController
@RequestMapping("/match/board/posts")
@RequiredArgsConstructor
public class MatchBoardPostController {

    private final MatchBoardPostServiceImpl matchBoardPostService;

    @GetMapping("/team")
    @Operation(summary = "나의 팀 정보 조회", description = "<strong>매치 게시글 생성</strong>을 위해 유저의 팀 정보를 조회한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "팀 정보 조회",
            content = @Content(schema = @Schema(implementation = UserTeamListRes.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND\n\nDELETED_USER",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<List<UserTeamListRes>> getMyTeams() {
        return ResponseEntity.ok(matchBoardPostService.getMyTeams());
    }

    @PostMapping
    @Operation(summary = "게시글 생성", description = "<strong>입력 받은 정보</strong>를 사용해 게시글을 생성한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "게시글 상세 정보",
            content = @Content(schema = @Schema(implementation = MatchBoardPostInfoRes.class))),
        @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND\n\nTEAM_NOT_FOUND\n\n"
            + "SPORT_CATEGORY_NOT_FOUND\n\nBOARD_NOT_FOUND\n\nBOARD_CATEGORY_NOT_FOUND",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<MatchBoardPostInfoRes> createPost(@RequestBody MatchBoardCreateReq matchBoardCreateReq) {
        return ResponseEntity.ok(matchBoardPostService.createPost(matchBoardCreateReq));
    }

    @GetMapping("/info/{postId}")
    @Operation(summary = "게시글 상세 정보 조회", description = "<strong>게시글 id</strong>를 사용해 게시글 정보를 조회한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "게시글 정보 조회",
            content = @Content(schema = @Schema(implementation = MatchBoardPostInfoRes.class))),
        @ApiResponse(responseCode = "404", description = "BOARD_NOT_FOUND",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<MatchBoardPostInfoRes> getPostInfo(@PathVariable("postId") long postId) {
        return ResponseEntity.ok(matchBoardPostService.getPostInfo(postId));
    }

    @PutMapping("/{postId}")
    @Operation(summary = "게시글 수정", description = "<strong>입력 받은 정보</strong>를 사용해 게시글을 수정한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "변경된 게시글 상세 정보",
            content = @Content(schema = @Schema(implementation = MatchBoardPostInfoRes.class))),
        @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND\n\nTEAM_NOT_FOUND\n\n"
            + "SPORT_CATEGORY_NOT_FOUND\n\nBOARD_NOT_FOUND\n\nBOARD_CATEGORY_NOT_FOUND",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<MatchBoardPostInfoRes> updatePost(@PathVariable("postId") long postId, @RequestBody MatchBoardUpdateReq matchBoardUpdateReq) {
        return ResponseEntity.ok(matchBoardPostService.updatePost(postId, matchBoardUpdateReq));
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 삭제", description = "<strong>입력 받은 정보</strong>를 사용해 게시글을 삭제한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "삭제되었습니다.", content = @Content),
    })
    public ResponseEntity<String> deletePost(@PathVariable("postId") long postId) {
        matchBoardPostService.deletePost(postId);
        return ResponseEntity.ok("삭제되었습니다.");
    }

}
