package com.service.chat.config.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { // (1)

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { // (2)
        registry.enableSimpleBroker("/sub"); // (3)
        registry.setApplicationDestinationPrefixes("/pub"); // (4)
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // (5)
        registry.addEndpoint("/ws") // ex ) ws://localhost:9000/stomp/chat
            .setAllowedOriginPatterns("*").withSockJS();
    }
}
