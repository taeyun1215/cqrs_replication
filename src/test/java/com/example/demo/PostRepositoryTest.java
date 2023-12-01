package com.example.demo;

import com.example.demo.post.PostJpaEntity;
import com.example.demo.post.PostJpaRepo;
import com.example.demo.user.adapter.out.persistence.SpringDataUserRepository;
import com.example.demo.user.adapter.out.persistence.UserJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostJpaRepo postJpaRepo;

    @Autowired
    private SpringDataUserRepository userRepository;

    @BeforeEach
    public void setup() {
        UserJpaEntity user = new UserJpaEntity(null, "user" + "@example.com", "password", "nickname" , "User" );
        userRepository.save(user);

        // 사용자 1명이 게시글을 10개 생성하고 저장합니다.
        for (int i = 1; i <= 10; i++) {
            PostJpaEntity post = new PostJpaEntity(null, "Title " + i, "Content " + i, user);
            postJpaRepo.save(post);
        }
    }


    @Test
    public void testNPlusOneProblem() {
        List<PostJpaEntity> posts = postJpaRepo.findAll();
        for (PostJpaEntity post : posts) {
            UserJpaEntity user = post.getUser();
            System.out.println("Post Title: " + post.getId() + ", User Name: " + user.getId());
        }
    }
}