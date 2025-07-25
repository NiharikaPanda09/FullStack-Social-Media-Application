package com.Niharika.social.controller;

import com.Niharika.social.models.Comment;
import com.Niharika.social.models.User;
import com.Niharika.social.service.CommentService;
import com.Niharika.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment,
                                 @RequestHeader("Authorization")String jwt,
                                 @PathVariable("postId") Integer postId) throws Exception {

       User user = userService.findUserByJWT(jwt);

       Comment createdComment = commentService.createComment(comment,postId,user.getId());

       return createdComment;
    }

    @PutMapping("api/comments/like/{commentId}")
    public Comment LikeComment(
                                 @RequestHeader("Authorization")String jwt,
                                 @PathVariable Integer commentId) throws Exception {

        User user = userService.findUserByJWT(jwt);

        Comment LikedComment = commentService.likeComments(commentId,user.getId());

        return LikedComment;
    }

}
