package com.Niharika.social.repository;

import com.Niharika.social.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    public List<Message> findByChatId(Integer chatId);
}
