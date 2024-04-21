package com.service.chat.message.prasentaion;

import com.service.chat.message.application.ChatRoomService;
import com.service.chat.message.application.RedisPubService;
import com.service.chat.message.application.RedisSubService;
import com.service.chat.message.dto.request.ChatLogRequest;
import com.service.chat.message.dto.request.ChatMessageRequest;
import com.service.chat.response.ResponseDto;
import com.service.chat.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        //메시지 보내기
        redisPubService.sendMessage(chatMessage);
        chatRoomService.addMessage(chatMessage);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, "success");
    }

    @GetMapping("/chat_messages")
    public ResponseEntity<?> getMessages(@RequestBody ChatLogRequest request){
        var messages = chatRoomService.getAllRoomChat(request);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, messages);
    }

    @GetMapping("/room")
    public ResponseEntity<?> createRoom(){
        var roomId = chatRoomService.createRoom();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, roomId);
    }
}
