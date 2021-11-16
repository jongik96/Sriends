package com.project.autonomous.chat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.project.autonomous.common.exception.ValidatorMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
public class ChatMessageReq {

    //    // 메시지 타입 : 입장, 채팅
//    public enum MessageType {
//        ENTER, TALK
//    }
//
//    private MessageType type; // 메시지 타입


    @Schema(description = "방 번호", example = "1")
    @NotNull(message = EMPTY_MESSAGE)
    private Long roomId; // 방번호

    @Schema(description = "보내는 사람", example = "1")
    @NotNull(message = EMPTY_MESSAGE)
    private Long sender; // 메시지 보낸사람

    @Schema(description = "메시지", example = "안녕하세요.")
    @NotNull(message = EMPTY_MESSAGE)
    private String message; // 메시지

    @Schema(description = "메시지 보낸 시간", example = "2021-11-16 17:00:00")
    @NotNull(message = EMPTY_MESSAGE)
    private LocalDateTime time; // 메시지 시간
}
