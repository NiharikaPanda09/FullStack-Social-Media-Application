package com.Niharika.social.controller;

import com.Niharika.social.models.Chat;
import com.Niharika.social.models.User;
import com.Niharika.social.request.CreateChatRequest;
import com.Niharika.social.service.ChatService;
import com.Niharika.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization")String jwt,
                           @RequestBody CreateChatRequest req) throws Exception {
        User requser = userService.findUserByJWT(jwt);
        User user2 = userService.findUserById(req.getUserid());
        Chat chat = chatService.createChat(requser,user2);

        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization")String jwt){
        User user = userService.findUserByJWT(jwt);
        List<Chat> chats = chatService.findUsersChat(user.getId());

        return chats;
    }
}
