package com.service.chat.message.application;

import com.service.chat.message.domain.Converter;
import com.service.chat.message.domain.Room;
import com.service.chat.message.dto.request.ChatLogRequest;
import com.service.chat.message.dto.request.ChatMessageRequest;
import com.service.chat.message.dto.response.ChatMessageResponse;
import com.service.chat.message.infrastructure.RoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final RoomRepository roomRepository;
    private Converter converter = new Converter();

    public Long createRoom() {
        var newRoom = Room.builder().build();
        roomRepository.save(newRoom);

        return newRoom.getId();
    }

    public void addMessage(ChatMessageRequest request){
       var room = roomRepository.findById(request.getRoomId()).get();
       room.getMessages().add(converter.toMessage(request));

       roomRepository.save(room);
    }

    public List<ChatMessageResponse> getAllRoomChat(ChatLogRequest request){
        var room =  roomRepository.findById(request.getRoomId()).get();

        return converter.toMessagesResponse(room.getMessages());
    }
}
