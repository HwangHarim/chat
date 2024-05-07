package com.service.chat.message.prasentaion;

import com.service.chat.message.application.ChatRoomService;
import com.service.chat.message.application.RedisPubService;
import com.service.chat.message.application.MessageService;
import com.service.chat.message.dto.request.AddAndDeleteMemberRequest;
import com.service.chat.message.dto.request.ChatMessageRequest;
import com.service.chat.response.ResponseDto;
import com.service.chat.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class RedisController {

    private final RedisPubService redisPubService;
    private final MessageService redisSubService;
    private final ChatRoomService chatRoomService;

    @GetMapping("/messages/{roomId}")
    public ResponseEntity<?> getMessages(@RequestParam Long roomId){
        var messages = chatRoomService.getAllRoomChat(roomId);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, messages);
    }

    @PostMapping("/messages/{roomId}/{memberId}")
    public ResponseEntity<?> sendMessage(
        @RequestParam Long roomId,
        @RequestParam String memberId,
        @RequestBody ChatMessageRequest chatMessage) {

        chatMessage.setRoomId(roomId);
        chatMessage.setSender(memberId);
        redisPubService.sendMessage(chatMessage);
        var message = chatRoomService.addMessage(chatMessage);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, message);
    }

    @GetMapping("/roomIndex/{roomId}")
    public ResponseEntity<?> getMemberRoom(@RequestParam("roomId") Long roomId){
        var members = chatRoomService.getAllRoomMember(roomId);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, members);
    }

    @PostMapping("/roomIndex/{roomId}/{memberId}")
    public ResponseEntity<?> addMemberRoom(
        @RequestParam("roomId") Long roomId,
        @RequestParam("memberId") String memberId
    ){
        var roomIndex = chatRoomService.setMember(new AddAndDeleteMemberRequest(roomId, memberId));

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, roomIndex);
    }

    @DeleteMapping("/roomIndex/{roomId}/{memberId}")
    public ResponseEntity<?> deleteMemberRoom(
        @RequestParam("roomId") Long roomId,
        @RequestParam("memberId") String memberId
    ){
        chatRoomService.deleteMember(new AddAndDeleteMemberRequest(roomId, memberId));
        var members = chatRoomService.getAllRoomMember(roomId);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, members);
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> getAllRoom(){
        var allRooms = chatRoomService.getAllRoom();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, allRooms);
    }

    @PostMapping("/rooms")
    public ResponseEntity<?> createRoom(){
        var roomId = chatRoomService.createRoom();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, roomId);
    }

    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<?> deleteRoom(@RequestParam("roomId") Long roomId){
        chatRoomService.deleteRoom(roomId);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, "삭제 완료");
    }
}