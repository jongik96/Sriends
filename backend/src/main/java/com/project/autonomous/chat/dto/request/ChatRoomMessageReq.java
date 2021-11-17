package com.project.autonomous.chat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.project.autonomous.common.exception.ValidatorMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomMessageReq {

    @Schema(description = "User Id값이 작은 것", example = "1")
    @NotNull(message = EMPTY_MESSAGE)
    private Long pub;

    @Schema(description = "User Id값이 큰 것", example = "2")
    @NotNull(message = EMPTY_MESSAGE)
    private Long sub;

}
