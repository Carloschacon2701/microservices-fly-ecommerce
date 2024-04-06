package org.carlos.chatservice.Services;


import lombok.RequiredArgsConstructor;
import org.carlos.chatservice.Dto.UserDTO;
import org.carlos.chatservice.Models.UserMongo;
import org.carlos.chatservice.OpenFeing.FlyCoreInterface;
import org.carlos.chatservice.Repository.UserChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.carlos.chatservice.Models.StatusMongo.OFFLINE;
import static org.carlos.chatservice.Models.StatusMongo.ONLINE;

@Service
@RequiredArgsConstructor
public class UserChatService {
    private final UserChatRepository userChatRepository;
    private final FlyCoreInterface flyCommerceInterface;

    public void saveUser(UserMongo user) {
        UserDTO userDB = flyCommerceInterface.getUser(user.getId()).getBody();
         if(userDB == null) {
             return;
         }

         user.setId(userDB.id());
        user.setName(userDB.name());
        user.setStatus(ONLINE);
        userChatRepository.save(user);
    }

    public void disconnect(UserMongo user){
        var storedUser = userChatRepository.findById(user.getId())
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
