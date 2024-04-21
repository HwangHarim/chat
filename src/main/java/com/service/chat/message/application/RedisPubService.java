package com.service.chat.message.application;

import com.service.chat.message.dto.request.ChatMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPubService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void sendMessage(ChatMessageRequest chatMessage) {
        redisTemplate.convertAndSend("chat", chatMessage);
    }
}
