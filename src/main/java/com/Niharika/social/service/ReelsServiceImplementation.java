package com.Niharika.social.service;

import com.Niharika.social.models.Reels;
import com.Niharika.social.models.User;
import com.Niharika.social.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImplementation implements ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reels createReels(Reels reel, User user) {
        Reels createReels = new Reels();


        createReels.setTitle(reel.getTitle());
        createReels.setUser(user);
        createReels.setVideo(reel.getVideo());
        return reelsRepository.save(createReels);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}
