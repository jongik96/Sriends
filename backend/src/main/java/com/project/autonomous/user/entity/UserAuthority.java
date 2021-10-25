package com.project.autonomous.user.entity;

// https://bcp0109.tistory.com/301
// 관리자용 계정과 일반 유저 계정 Enum class
// Spring Security에서 내부적으로 사용하기 때문에 강제됨
public enum UserAuthority {
    ROLE_USER, ROLE_ADMIN
}
