package com.Niharika.social.service;

import com.Niharika.social.models.Story;
import com.Niharika.social.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StoryService {

    public Story createStory(Story story, User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
