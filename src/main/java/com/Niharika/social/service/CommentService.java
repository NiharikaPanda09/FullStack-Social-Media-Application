package com.Niharika.social.service;

import com.Niharika.social.models.Comment;


public interface CommentService {
    public Comment createComment(Comment comment,Integer postId,Integer userId) throws Exception;
   public Comment likeComments(Integer commentId,Integer userId) throws Exception;
   public Comment findCommentById(Integer commentId)throws Exception;

}
