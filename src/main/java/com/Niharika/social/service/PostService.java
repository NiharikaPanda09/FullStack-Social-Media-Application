package com.Niharika.social.service;

import com.Niharika.social.models.Post;

import java.util.List;

public interface PostService {

    Post createNewpost(Post post, Integer userId) throws Exception;

    String deletePost(Integer postId,Integer userId) throws Exception;

    List<Post> findPostByUserId(Integer userId);

    Post findPostById(Integer postId) throws Exception;

    List<Post> findAllPost();

    Post savedPost(Integer postId,Integer userId) throws Exception;

    Post likePost(Integer postId,Integer userId) throws Exception;


}
