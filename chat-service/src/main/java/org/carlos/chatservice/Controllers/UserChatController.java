package org.carlos.chatservice.Controllers;


import lombok.RequiredArgsConstructor;
import org.carlos.chatservice.Models.UserMongo;
import org.carlos.chatservice.OpenFeing.FlyCommerceInterface;
import org.carlos.chatservice.Services.UserChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserChatController {
    private final UserChatService userChatService;
    private final FlyCommerceInterface flyCommerceInterface;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public UserMongo addUser(@Payload UserMongo user) {
        System.out.println("User added: " + user.getName());
         userChatService.saveUser(user);
         return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public UserMongo disconnectUser(UserMongo user) {
        userChatService.disconnect(user);
        return user;
    }

    @MessageMapping("/user/topic")
    public void topic() {
        System.out.println("Topic");
    }

    @GetMapping("/api/chat/users")
    public ResponseEntity<List<UserMongo>> findConnectedUsers() {
        return ResponseEntity.ok(userChatService.getOnlineUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(flyCommerceInterface.getUser(id));
    }
}
