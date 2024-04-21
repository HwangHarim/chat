package com.service.chat.message.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatLogResponse {
    private Long roomId;
    private List<ChatMessageResponse> messages;
}
