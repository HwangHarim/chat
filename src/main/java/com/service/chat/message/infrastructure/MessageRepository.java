package com.service.chat.message.infrastructure;

import com.service.chat.message.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

}
