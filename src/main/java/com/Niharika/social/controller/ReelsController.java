package com.Niharika.social.controller;

import com.Niharika.social.models.Reels;
import com.Niharika.social.models.User;
import com.Niharika.social.service.ReelsService;
import com.Niharika.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;


    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt){

        User reqUser = userService.findUserByJWT(jwt);
        Reels createdReels = reelsService.createReels(reel,reqUser);
        return createdReels;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels( ){


        List<Reels> reels = reelsService.findAllReels();
        return reels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUsersReel(@PathVariable Integer userId ) throws Exception {


        List<Reels> reels = reelsService.findUsersReel(userId);
        return reels;
    }
}
