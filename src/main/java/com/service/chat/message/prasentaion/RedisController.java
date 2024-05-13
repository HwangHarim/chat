package com.service.chat.message.prasentaion;

import com.service.chat.message.application.ChatRoomService;
import com.service.chat.message.dto.request.AddAndDeleteMemberRequest;
import com.service.chat.message.dto.request.ChatMessageRequest;
import com.service.chat.message.dto.request.SendMessageRequest;
import com.service.chat.response.ResponseDto;
import com.service.chat.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class RedisController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/messages/{roomId}")
    public ResponseEntity<?> getMessages(@PathVariable Long roomId) {
        var messages = chatRoomService.getAllRoomChat(roomId);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, messages);
    }

    @PostMapping("/messages/{roomId}/{memberId}")
    public ResponseEntity<?> sendMessage(
            @PathVariable Long roomId,
            @PathVariable String memberId,
            @RequestBody SendMessageRequest request
    ) {
        var msg = chatRoomService.addMessage(ChatMessageRequest.builder()
                .roomId(roomId)
                .sender(memberId)
                .context(request.getContext())
                .build());

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, msg);
    }

    @GetMapping("/roomIndex/{roomId}")
    public ResponseEntity<?> getMemberRoom(@PathVariable("roomId") Long roomId) {
        var members = chatRoomService.getAllRoomMember(roomId);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, members);
    }

    @PostMapping("/roomIndex/{roomId}/{memberId}")
    public ResponseEntity<?> addMemberRoom(
            @PathVariable("roomId") Long roomId,
            @PathVariable("memberId") String memberId
    ) {
        var roomIndex = chatRoomService.setMember(new AddAndDeleteMemberRequest(roomId, memberId));

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, roomIndex);
    }

    @DeleteMapping("/roomIndex/{roomId}/{memberId}")
    public ResponseEntity<?> deleteMemberRoom(
            @PathVariable("roomId") Long roomId,
            @PathVariable("memberId") String memberId
    ) {
        chatRoomService.deleteMember(new AddAndDeleteMemberRequest(roomId, memberId));
        var members = chatRoomService.getAllRoomMember(roomId);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, members);
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> getAllRoom() {
        var allRooms = chatRoomService.getAllRoom();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, allRooms);
    }

    @PostMapping("/rooms")
    public ResponseEntity<?> createRoom() {
        var roomId = chatRoomService.createRoom();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, roomId);
    }

    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable("roomId") Long roomId) {
        chatRoomService.deleteRoom(roomId);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, "삭제 완료");
    }
}