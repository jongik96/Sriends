package com.project.autonomous.picture.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(title = "파일 삭제 요청", description = "사진, 포폴 변경할 때 기존것을 삭제하기 위한 Request Dto Class")
public class DeletePictureRequestDto {

    @Schema(description = "사진 고유 uuid", example = "97534f05-7e7f-425d-ac3e-aae8acee8a42")
    private String uuid;

}
