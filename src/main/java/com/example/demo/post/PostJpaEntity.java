package com.example.demo.post;

import com.example.demo.user.adapter.out.persistence.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
public class PostJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;
}