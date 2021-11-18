package com.project.autonomous.chat.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class GetMSGByPartnerIdRoomIdRes {

    private Long roomId;

    private List<GetMSGByPartnerIdRes> list;

}
