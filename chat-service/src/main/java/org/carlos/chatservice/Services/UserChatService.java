package org.carlos.chatservice.Services;


import lombok.RequiredArgsConstructor;
import org.carlos.chatservice.Models.UserMongo;
import org.carlos.chatservice.Repository.UserChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.carlos.chatservice.Models.StatusMongo.OFFLINE;
import static org.carlos.chatservice.Models.StatusMongo.ONLINE;

@Service
@RequiredArgsConstructor
public class UserChatService {
    private final UserChatRepository userChatRepository;

    public void saveUser(UserMongo user) {
        user.setStatus(ONLINE);
        userChatRepository.save(user);
    }

    public void disconnect(UserMongo user){
        var storedUser = userChatRepository.findById(user.getName())
                .orElse(null);

        if (storedUser != null) {
            storedUser.setStatus(OFFLINE);
            userChatRepository.save(storedUser);
        }
    }

    public List<UserMongo> getOnlineUsers() {
        return userChatRepository.findAllByStatus(ONLINE);
    }
}
