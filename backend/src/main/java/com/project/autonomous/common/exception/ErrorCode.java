package com.project.autonomous.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 정의된 로직의 조건을 위반하는 경우 */
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    MEMBER_COUNT_BELOW_ZERO(BAD_REQUEST, "인원은 0 아래가 될 수 없습니다."),
    COMMON_MEMBER_CANNOT_REMOVE(BAD_REQUEST, "팀원은 강퇴 기능을 가지고 있지 않습니다."),
    ONLY_CAN_REMOVE_COMMON(BAD_REQUEST, "팀원만 추방 시킬 수 있습니다."),
    CANNOT_APPLY(BAD_REQUEST, "신청할 수 없습니다."),
    ALREADY_JOIN(BAD_REQUEST, "이미 가입되어있습니다."),
    ALREADY_APPLY(BAD_REQUEST, "이미 신청되어있습니다."),
    NOT_ALLOW_TYPE(BAD_REQUEST, "JPG와 PNG 형식만 지원합니다."),
    NO_INTERESTING_ITEMS(BAD_REQUEST, "흥미있는 종목 설정이 없습니다."),
    STILL_YOU_HAVE_SREINEDS(BAD_REQUEST, "아직 대표인 스렌즈가 존재합니다."),
    CANNOT_LEAVE_LEADER(BAD_REQUEST, "팀 대표는 탈퇴할 수 없습니다."),
    CANNOT_KICKOUT_MANAGER(BAD_REQUEST, "매니저는 강퇴할 수 없습니다."),

//    EMPTY_MESSAGE(BAD_REQUEST, "비어있는 항목을 입력해주세요."),
//    EMAIL_MESSAGE(BAD_REQUEST, "올바른 이메일 형식이 아닙니다."),
//    MEMBER_PW_MESSAGE(BAD_REQUEST, "비밀번호는 영어와 숫자를 포함해서 8자 이상 20자 이내로 입력해주세요."),
//    RESERVATION_PW_MESSAGE(BAD_REQUEST, "비밀번호는 숫자 4자리만 입력 가능합니다."),
//    ORGANIZATION_MESSAGE(BAD_REQUEST, "조직명은 특수문자 없이 20자 이내로 작성 가능합니다."),
//    NAME_MESSAGE(BAD_REQUEST, "이름은 특수문자(-_!?.,)를 포함하여 20자 이내로 작성 가능합니다."),
//    PRESET_NAME_MESSAGE(BAD_REQUEST, "프리셋 이름은 공백과 특수문자(-_!?.,)를 포함하여 20자 이내로 작성 가능합니다."),
//    DESCRIPTION_MESSAGE(BAD_REQUEST, "예약 내용은 100자 이내로 작성 가능합니다."),
//    FORMAT_MESSAGE(BAD_REQUEST, "날짜 및 시간 데이터 형식이 올바르지 않습니다."),
//    DAY_OF_WEEK_MESSAGE(BAD_REQUEST, "올바른 요일 형식이 아닙니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),
    UNAUTHORIZED_CHANGE(UNAUTHORIZED, "변경 권한을 가지고 있지 않습니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    USER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    TEAM_NOT_FOUND(NOT_FOUND, "해당 팀 정보를 찾을 수 없습니다"),
    DELETED_USER(NOT_FOUND, "탈퇴한 유저입니다."),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "로그아웃 된 사용자입니다"),
    FILE_NOT_FOUND(NOT_FOUND, "해당 파일 정보를 찾을 수 없습니다"),
    CITY_NOT_FOUND(NOT_FOUND, "해당 지역 정보를 찾을 수 없습니다."),
    RECRUITMENT_STATE_NOT_FOUND(NOT_FOUND, "해당 모집 상태를 찾을 수 없습니다."),
    AUTHORITY_NOT_FOUND(NOT_FOUND, "해당 권한을 찾을 수 없습니다."),
    APPLIY_FORM_NOT_FOUND(NOT_FOUND, "해당 신청서를 찾을 수 없습니다."),
    BOARD_NOT_FOUND(NOT_FOUND, "해당 게시판을 찾을 수 없습니다."),
    CONTENT_NOT_FOUND(NOT_FOUND, "내용을 찾을 수 없습니다."),
    ARTICLE_NOT_FOUND(NOT_FOUND, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(NOT_FOUND, "해당 댓글을 찾을 수 없습니다."),
    TOKEN_NOT_FOUND(NOT_FOUND, "해당 토큰을 찾을 수 없습니다"),
    LIST_NOT_FOUND(NOT_FOUND, "해당 리스트를 찾을 수 없습니다"),
    SPORT_CATEGORY_NOT_FOUND(NOT_FOUND, "해당 종목을 찾을 수 없습니다"),
    BOARD_CATEGORY_NOT_FOUND(NOT_FOUND, "해당 분류를 찾을 수 없습니다"),
    CHATROOM_NOT_FOUND(NOT_FOUND,"해당 채팅방을 찾을 수 없습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
