package com.project.autonomous.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public class InterestReq {

    @Schema(description = "관심 스포츠", example = "[\"축구\", \"풋살\"]")
    private List<String> interests;

}
