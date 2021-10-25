package com.project.autonomous.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReq {
    String email;
    String password;
}
