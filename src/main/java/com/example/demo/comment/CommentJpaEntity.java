package com.example.demo.comment;

import com.example.demo.post.PostJpaEntity;
import com.example.demo.user.adapter.out.persistence.UserJpaEntity;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class CommentJpaEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostJpaEntity post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;
}