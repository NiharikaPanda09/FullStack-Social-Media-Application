package com.Niharika.social.service;

import com.Niharika.social.exceptions.UserException;
import com.Niharika.social.models.User;

import java.util.List;

public interface UserService {

    public User RegisterUser(User user);

    public User findUserById(Integer userId) throws UserException;

    public User findUserByEmail(String email);

    public User followUser(Integer userId1,Integer userId2) throws UserException;

    public User updateUser(User user,Integer userId) throws UserException;

    public List<User> searchUser(String query);

    public User findUserByJWT(String jwt);
}
