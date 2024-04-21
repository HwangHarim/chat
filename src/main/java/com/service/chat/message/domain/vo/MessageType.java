package com.service.chat.message.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    TALK("TALK","대화중입니다."),
    NOT_TALK("TALK","대화중입니다.");

    private String type;
    private String status;
}