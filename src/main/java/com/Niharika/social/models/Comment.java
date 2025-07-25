package com.Niharika.social.models;

import jakarta.persistence.*;

import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String content;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> liked = new ArrayList<>();

    public Integer getId() {
        return Id;
    }

    private LocalDateTime createdAt;
     public Comment(){

       }

    public Comment(Integer id, String content, User user, List<User> liked, LocalDateTime createdAt) {
        Id = id;
        this.content = content;
        this.user = user;
        this.liked = liked;
        this.createdAt = createdAt;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLiked() {
        return liked;
    }

    public void setLiked(List<User> liked) {
        this.liked = liked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
