package com.service.chat.message.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomInfoResponse {
    private String memberId;
    private Long roomId;
    private int roomIndex;
}
