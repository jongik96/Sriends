package com.project.autonomous.picture.dto;

import com.project.autonomous.picture.entity.Picture;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PictureInfoDto {

    @Schema(description = "사진 고유 uuid", example = "97534f05-7e7f-425d-ac3e-aae8acee8a42")
    private String id;

    @Schema(description = "사진명", example = "박범진사진.jpg")
    private String name;

    @Schema(description = "사진 타입", example = "image/jpeg")
    private String type;

    @Schema(description = "이미지 저장 경로", example = "not yet")
    private String imageUrl;

    public static PictureInfoDto from(Picture picture) {
        if (picture == null) {
            return null;
        }
        return PictureInfoDto.builder()
                .id(picture.getId())
                .name(picture.getName())
                .type(picture.getType())
                .imageUrl(picture.getImageUrl())
                .build();
    }

}
