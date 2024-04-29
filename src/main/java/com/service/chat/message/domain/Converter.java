package com.service.chat.message.domain;

import com.service.chat.message.dto.request.ChatMessageRequest;
import com.service.chat.message.dto.response.ChatMessageResponse;
import java.util.ArrayList;
import java.util.List;

public class Converter {

    public ChatMessageResponse toConvertChatMessageResponse(ChatMessageRequest request){
        return ChatMessageResponse.builder()
            .roomId(request.getRoomId())
            .sender(request.getSender())
            .context(request.getContext())
            .build();
    }

    public Message toMessage(ChatMessageRequest request){
        return Message.builder()
            .roomId(request.getRoomId())
            .uuid(request.getSender())
            .context(request.getContext())
            .build();
    }

    public List<ChatMessageResponse> toMessagesResponse(List<Message> messages){
        List<ChatMessageResponse> answer = new ArrayList<>();
        for(Message message : messages){
            answer.add(
                ChatMessageResponse.builder()
                    .roomId(message.getRoomId())
                    .sender(message.getUuid())
                    .context(message.getContext())
                    .build()
            );
        }
        return answer;
    }
}
