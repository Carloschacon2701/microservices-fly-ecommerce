package org.carlos.chatservice.Repository;


import org.carlos.chatservice.Models.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findByChatId (String chatId);
}
