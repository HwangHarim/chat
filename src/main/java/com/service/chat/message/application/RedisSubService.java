package com.service.chat.message.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.chat.message.domain.Converter;
import com.service.chat.message.dto.request.ChatMessageRequest;
import com.service.chat.message.dto.response.ChatMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSubService implements MessageListener {
    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;
    private final Converter converter = new Converter();
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            ChatMessageRequest roomMessage = objectMapper.readValue(publishMessage, ChatMessageRequest.class);
            ChatMessageResponse chatMessageResponse = converter.toConvertChatMessageResponse(roomMessage);

            messagingTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getRoomId(), chatMessageResponse);
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }
}
