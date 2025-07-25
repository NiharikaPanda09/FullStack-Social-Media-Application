package com.Niharika.social.controller;

import com.Niharika.social.exceptions.UserException;
import com.Niharika.social.models.User;
import com.Niharika.social.repository.UserRepository;
import com.Niharika.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;



    @GetMapping("api/user")
    public List<User> getUser(){

      List<User> user = userRepository.findAll();
      return user;

    }

    @GetMapping("/api/user/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {

       User user = userService.findUserById(id);
       return user;

    }



@PutMapping("/api/user")
public User updateUser(@RequestHeader("Authorization") String jwt,
                       @RequestBody User user) throws
        UserException {

        User reqUser = userService.findUserByJWT(jwt);
        User updatedUser = userService.updateUser(user, reqUser.getId());
        return updatedUser;
    }

    @PutMapping("/user/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt,
                                  @PathVariable Integer userId2) throws UserException {
        User reqUser = userService.findUserByJWT(jwt);
        User user = userService.followUser(reqUser.getId(),userId2);
        return user;

    }

    @GetMapping("/user/search")
public List<User> searchUser(@RequestParam("query") String query){
        List<User> user = userService.searchUser(query);
        return user;
}

      @GetMapping("/api/user/profile")
      public User getUserFromToken(@RequestHeader("Authorization") String jwt){
//          System.out.println("jwt----" +jwt);
          User user = userService.findUserByJWT(jwt);
          user.setPassword(null);
       return user;
       }
}
