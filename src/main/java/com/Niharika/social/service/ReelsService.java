package com.Niharika.social.service;

import com.Niharika.social.models.Reels;
import com.Niharika.social.models.User;

import java.util.List;

public interface ReelsService {
    public Reels createReels(Reels reel, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReel(Integer userId) throws Exception;
}
