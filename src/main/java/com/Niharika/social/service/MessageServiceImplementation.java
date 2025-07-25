package com.Niharika.social.service;

import com.Niharika.social.models.Chat;
import com.Niharika.social.models.Message;
import com.Niharika.social.models.User;
import com.Niharika.social.repository.ChatRepository;
import com.Niharika.social.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService{

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {
        Message message = new Message();

        Chat chat = chatService.findChatById(chatId);
        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimeStamp(LocalDateTime.now());
       Message Savedmessage =  messageRepository.save(message);
       chat.getMessages().add(Savedmessage);
       chatRepository.save(chat);
        return Savedmessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
