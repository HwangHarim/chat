package com.service.chat.message.prasentaion;

import com.service.chat.message.application.ChatRoomService;
import com.service.chat.message.application.RedisPubService;
import com.service.chat.message.application.RedisSubService;
import com.service.chat.message.dto.request.AddAndDeleteMemberRequest;
import com.service.chat.message.dto.request.ChatLogRequest;
import com.service.chat.message.dto.request.ChatMessageRequest;
import com.service.chat.message.dto.request.MembersRequest;
import com.service.chat.response.ResponseDto;
import com.service.chat.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class RedisController {

    private final RedisPubService redisPubService;
    private final RedisSubService redisSubService;
    private final ChatRoomService chatRoomService;

    @PostMapping("/messages")
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessageRequest chatMessage) {
        redisPubService.sendMessage(chatMessage);
        var message = chatRoomService.addMessage(chatMessage);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, message);
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getMessages(@RequestBody ChatLogRequest request){
        var messages = chatRoomService.getAllRoomChat(request);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, messages);
    }

    @GetMapping("/room")
    public ResponseEntity<?> createRoom(){
        var roomId = chatRoomService.createRoom();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, roomId);
    }

    @PostMapping("/roomIndex")
    public ResponseEntity<?> addMemberRoom(@RequestBody AddAndDeleteMemberRequest request){
        var roomIndex = chatRoomService.setMember(request);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, roomIndex);
    }

    @GetMapping("/roomIndex")
    public ResponseEntity<?> getMemberRoom(@RequestBody MembersRequest request){
        var members = chatRoomService.getAllRoomMember(request.getRoomId());

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, members);
    }

    @DeleteMapping("/roomIndex")
    public ResponseEntity<?> deleteMemberRoom(@RequestBody AddAndDeleteMemberRequest request){
        chatRoomService.deleteMember(request);
        var members = chatRoomService.getAllRoomMember(request.getRoomId());

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, members);
    }

    @GetMapping("/allRooms")
    public ResponseEntity<?> getAllRoom(){
        var allRooms = chatRoomService.getAllRoom();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, allRooms);
    }
}