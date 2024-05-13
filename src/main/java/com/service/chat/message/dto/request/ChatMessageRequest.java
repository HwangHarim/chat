package com.service.chat.message.dto.request;

import lombok.*;

@Getter
@Builder
public class ChatMessageRequest {
    private Long roomId;
    private String sender;
    private String context;
}
