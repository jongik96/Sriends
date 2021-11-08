package com.project.autonomous.matchboard.comments.dto.request;

import static com.project.autonomous.common.exception.ValidatorMessage.EMPTY_MESSAGE;

import com.project.autonomous.matchboard.comments.entity.MatchBoardComment;
import com.project.autonomous.matchboard.posts.entity.MatchBoardPost;
import com.project.autonomous.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MatchBoardUpdateCommentReq {

    @Schema(description = "댓글 내용", example = "용병 신청합니다.")
    @NotBlank(message = EMPTY_MESSAGE)
    @Size(max = 500)
    private String content;

}
