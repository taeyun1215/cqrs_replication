package com.example.demo.comment;

import com.example.demo.post.PostJpaEntity;
import com.example.demo.user.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class CommentJpaEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserJpaEntity userJpaEntity;

    public void addUser(UserJpaEntity userJpaEntity) {
        this.userJpaEntity = userJpaEntity;
    }
}