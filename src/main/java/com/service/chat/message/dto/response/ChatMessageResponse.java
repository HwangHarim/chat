package com.service.chat.message.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessageResponse {
    private Long roomId;
    private String sender;
    private String context;
}