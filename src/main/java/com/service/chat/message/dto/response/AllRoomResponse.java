package com.service.chat.message.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AllRoomResponse {
    private List<RoomResponse> allRoomList;
}
