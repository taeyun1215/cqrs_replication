package com.example.demo;

import com.example.demo.post.PostJpaEntity;
import com.example.demo.post.PostJpaRepo;
import com.example.demo.user.adapter.out.persistence.SpringDataUserRepository;
import com.example.demo.user.adapter.out.persistence.UserJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostJpaRepo postJpaRepo;

    @Autowired
    private SpringDataUserRepository userRepository;

    @BeforeEach
    public void setup() {
        // 사용자와 게시글을 각각 10개씩 생성하고 저장합니다.
        for (int i = 1; i <= 10; i++) {
            UserJpaEntity user = new UserJpaEntity(null, "user" + i + "@example.com", "password", "nickname" + i, "User " + i);
            userRepository.save(user);

            PostJpaEntity post = new PostJpaEntity(null, "Title " + i, "Content " + i, user);
            postJpaRepo.save(post);
        }
    }


    @Test
    public void testNPlusOneProblem() {
        List<PostJpaEntity> posts = postJpaRepo.findAll();
        for (PostJpaEntity post : posts) {
            UserJpaEntity user = post.getUser();
            System.out.println("Post Title: " + post.getTitle() + ", User Name: " + user.getName());
        }
    }
}