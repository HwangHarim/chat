package com.service.chat.redisTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.service.chat.message.domain.Message;
import com.service.chat.message.infrastructure.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class RedisTest {

    @Autowired
    private MessageRepository repository;

    @Test
    void save(){
        Message message = new Message(1L, "title", "content");

        repository.save(message);

        Message findMessage = repository.findById(message.getId()).get();
        assertThat(findMessage.getTitle()).isEqualTo("title");
    }
}
