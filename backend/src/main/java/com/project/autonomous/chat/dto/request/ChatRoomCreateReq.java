package com.project.autonomous.chat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

import static com.project.autonomous.common.exception.ValidatorMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
public class ChatRoomCreateReq {

    @Schema(description = "User Id값이 작은 것", example = "1")
    @NotNull(message = EMPTY_MESSAGE)
    private long pub;

    @Schema(description = "User Id값이 큰 것", example = "2")
    @NotNull(message = EMPTY_MESSAGE)
    private long sub;
}
