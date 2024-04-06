package org.carlos.chatservice.Repository;


import org.carlos.chatservice.Models.StatusMongo;
import org.carlos.chatservice.Models.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserChatRepository extends MongoRepository<UserMongo, Integer> {
    List<UserMongo> findAllByStatus(StatusMongo status);
}
