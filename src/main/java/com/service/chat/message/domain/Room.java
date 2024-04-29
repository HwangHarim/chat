package com.service.chat.message.domain;

import com.service.chat.message.domain.vo.RoomMember;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("chat-room")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Room {

    @Id
    private Long id;
    private RoomMember roomMember = new RoomMember();
    private List<Message> messages =  new ArrayList<>();
}
