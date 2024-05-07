package com.service.chat.message.application;

import com.service.chat.message.domain.Converter;
import com.service.chat.message.domain.Room;
import com.service.chat.message.dto.request.AddAndDeleteMemberRequest;
import com.service.chat.message.dto.request.ChatLogRequest;
import com.service.chat.message.dto.request.ChatMessageRequest;
import com.service.chat.message.dto.response.AllRoomMembersResponse;
import com.service.chat.message.dto.response.AllRoomResponse;
import com.service.chat.message.dto.response.ChatMessageResponse;
import com.service.chat.message.dto.response.RoomInfoResponse;
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

    public ChatMessageResponse addMessage(ChatMessageRequest request){
       var room = roomRepository.findById(request.getRoomId()).get();
       room.getMessages().add(converter.toMessage(request));
       roomRepository.save(room);

       return ChatMessageResponse.builder()
           .roomId(request.getRoomId())
           .roomIndex(room.getRoomMember().getMemberIndex(request.getSender()))
           .sender(request.getSender())
           .context(request.getContext())
           .build();
    }

    public List<ChatMessageResponse> getAllRoomChat(ChatLogRequest request){
        var room =  roomRepository.findById(request.getRoomId()).get();

        return converter.toMessagesResponse(room.getMessages());
    }

    public int getRoomIndex(AddAndDeleteMemberRequest request){
        var room = roomRepository.findById(request.getRoomId()).get();
        room.getRoomMember().addMember(request.getMemberId());
        roomRepository.save(room);
        return room.getRoomMember().getMemberIndex(request.getMemberId());
    }

    public AllRoomMembersResponse getAllRoomMember(Long roomId){
        var room = roomRepository.findById(roomId).get();

        return new AllRoomMembersResponse(room.getRoomMember().getMemberArr());
    }

    public void deleteMember(AddAndDeleteMemberRequest request){
        var room = roomRepository.findById(request.getRoomId()).get();
        room.getRoomMember().deleteMember(request.getMemberId());
        roomRepository.save(room);
    }

    public RoomInfoResponse setMember(AddAndDeleteMemberRequest request){
        return RoomInfoResponse.builder()
            .memberId(request.getMemberId())
            .roomId(request.getRoomId())
            .roomIndex(getRoomIndex(request))
            .build();
    }

    public AllRoomResponse getAllRoom(){
        return converter.toAllRoomResponse(roomRepository.findAll().iterator());
    }
}