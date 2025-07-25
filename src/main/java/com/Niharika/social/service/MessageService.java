package com.Niharika.social.service;

import com.Niharika.social.models.Chat;
import com.Niharika.social.models.Message;
import com.Niharika.social.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MessageService {
    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;

}
