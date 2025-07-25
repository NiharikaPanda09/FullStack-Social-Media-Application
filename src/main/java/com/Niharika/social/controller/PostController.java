package com.Niharika.social.controller;

import com.Niharika.social.models.Post;
import com.Niharika.social.models.User;
import com.Niharika.social.response.ApiResponse;
import com.Niharika.social.service.PostService;
import com.Niharika.social.service.UserService;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
     UserService userService;
    @PostMapping("/api/post")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt,
                                           @RequestBody Post post) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
    Post createdPost = postService.createNewpost(post,reqUser.getId());

    return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Integer postId) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        String message = postService.deletePost(postId, reqUser.getId());
        ApiResponse apiResponse= new ApiResponse(message,true);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/api/post/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }


    @GetMapping("/api/post/user/{userid}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId){

        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);

    }

    @GetMapping("/api/post")
    public ResponseEntity<List<Post>> findAllPost(){

        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<>(posts,HttpStatus.OK);

    }
    @PutMapping("/api/post/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        Post post = postService.savedPost(postId, reqUser.getId());
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }
    @PutMapping("/api/post/like/{postId}")
    public ResponseEntity<Post> likedPostHandler(@PathVariable Integer postId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
       User reqUser = userService.findUserByJWT(jwt);
        Post post = postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }
}
