package com.service.chat.message.domain;

import com.service.chat.message.domain.vo.RoomLimit;
import com.service.chat.message.dto.request.ChatMessageRequest;
import com.service.chat.message.dto.response.AllRoomResponse;
import com.service.chat.message.dto.response.ChatMessageResponse;
import com.service.chat.message.dto.response.RoomResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public AllRoomResponse toAllRoomResponse(Iterator<Room> rooms){
        List<RoomResponse> result = StreamSupport.stream(Spliterators.spliteratorUnknownSize(rooms, 0), false)
            .map(room -> new RoomResponse(room.getId()))
            .collect(Collectors.toList());
        return new AllRoomResponse(result);
    }
}
