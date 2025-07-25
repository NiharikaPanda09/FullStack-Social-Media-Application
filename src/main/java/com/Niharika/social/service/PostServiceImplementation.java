package com.Niharika.social.service;

import com.Niharika.social.models.Post;


import com.Niharika.social.models.User;
import com.Niharika.social.repository.UserRepository;
import com.Niharika.social.service.UserService;
import com.Niharika.social.repository.PostRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @Override
    public Post createNewpost(Post post, Integer userId) throws Exception {

        User user = userService.findUserById(userId);
        Post newPost = new Post();
         newPost.setCaption(post.getCaption());
         newPost.setImage(post.getImage());
         newPost.setUser(user);
         newPost.setVideo(post.getVideo());
         newPost.setLiked(new ArrayList<>());
         newPost.setCreatedAt(LocalDateTime.now());
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(!Objects.equals(post.getUser().getId(), user.getId())){
            throw new Exception("You cant delete another's post");
        }
        postRepository.delete(post);
        return "Post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {

        Optional<Post> opt = postRepository.findById(postId);
        if(opt.isEmpty()){
            throw new Exception("Post not exits");
        }
        return opt.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else {
            post.getLiked().add(user);
        }

        return postRepository.save(post);
    }
}
