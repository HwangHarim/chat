package com.service.chat.message.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "Message")
@Getter
public class Message {

    @Id
    private Long id;

    private String title;
    private String content;

    public Message(long id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

}
