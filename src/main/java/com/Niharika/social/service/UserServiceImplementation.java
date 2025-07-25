package com.Niharika.social.service;

import com.Niharika.social.config.JwtProvider;
import com.Niharika.social.exceptions.UserException;
import com.Niharika.social.models.User;
import com.Niharika.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User RegisterUser(User user) {
        User newUser =new User();


        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFollowers(new ArrayList<>());
        newUser.setFollowings(new ArrayList<>());
        newUser.setSavedPost(new ArrayList<>());

        User savedUser=userRepository.save(newUser);

        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws UserException {
        //optional user in the sense that the user can be or cannot be present
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not found with userid"+userId);


    }

    @Override
    public User findUserByEmail(String email) {
      User user = userRepository.findByEmail(email);
      return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {
        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);
        return reqUser;
    }

    @Override
    public User updateUser(User user,Integer userId) throws UserException {
        Optional<User> user1 = userRepository.findById(userId);

        if (user1.isEmpty()) {
            throw new UserException("user not present with user id" + userId);
        }

        User oldUser = user1.get();
        if (user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName()!= null) {
            oldUser.setLastName(user.getLastName());
        }
        if (user.getEmail()!= null) {
            oldUser.setEmail(user.getEmail());
        }
        if (user.getId()!=null) {
            oldUser.setId(user.getId());
        }
      if(user.getGender()!=null){
          oldUser.setGender(user.getGender());
      }
       User updatedUser = userRepository.save(oldUser);
        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
       return userRepository.searchUser(query);

    }

    @Override
    public User findUserByJWT(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }
}
