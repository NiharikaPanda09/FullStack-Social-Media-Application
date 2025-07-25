package com.Niharika.social.controller;

import com.Niharika.social.models.Message;
import com.Niharika.social.models.User;
import com.Niharika.social.service.MessageService;
import com.Niharika.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessage {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req,

                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJWT(jwt);
        Message message = messageService.createMessage(user,chatId,req);
       return message;
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatsMessages(

                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJWT(jwt);
        List<Message> messages = messageService.findChatsMessages(chatId);
        return messages;
    }
}
