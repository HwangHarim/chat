package com.service.chat.message.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfo {
    private String memberId;
    private int roomIndex = -1;
}
