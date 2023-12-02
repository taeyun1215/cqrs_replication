package com.example.demo;

import com.example.demo.post.PostJpaEntity;
import com.example.demo.post.PostJpaRepo;
import com.example.demo.user.adapter.out.persistence.SpringDataUserRepository;
import com.example.demo.user.adapter.out.persistence.UserJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@SpringBootTest
@DisplayName("1:N 관계에서 N에서 Lazy Loading 을 사용할 때 발생하는 N+1 문제를 확인합니다.")
public class JPA_OneToMany_LazyLoading_Test1 {

    @Autowired
    private PostJpaRepo postJpaRepo;

    @Autowired
    private SpringDataUserRepository userJpaRepo;

    @BeforeEach
    public void setup() {
        for (int i = 1; i <= 10; i++) {
            // UserJpaEntity 객체 생성
            UserJpaEntity userJpaEntity = UserJpaEntity.builder()
                    .email("user" + i + "@example.com")
                    .password("password")
                    .nickname("nickname" + i)
                    .name("User " + i)
                    .build();

            // PostJpaEntity 객체 생성
            PostJpaEntity postJpaEntity = PostJpaEntity.builder()
                    .title("Title " + i)
                    .content("Content " + i)
                    .build();

            // UserJpaEntity에 PostJpaEntity를 추가
            userJpaEntity.addPost(postJpaEntity);

            // PostJpaEntity에 UserJpaEntity를 추가
            postJpaEntity.addUser(userJpaEntity);

            // UserJpaEntity를 저장 (CascadeType.ALL에 의해 PostJpaEntity도 저장됩니다)
            userJpaRepo.save(userJpaEntity);
        }
    }

    @Test
    public void testNPlusOneProblem() {
        List<UserJpaEntity> userJpaEntities = userJpaRepo.findAll();

        // N+1 문제가 발생합니다. 1(userJpaRepo.findAll()) + 10(userJpaEntities.stream())번의 쿼리가 실행됩니다.
        userJpaEntities.stream()
                .map(userJpaEntity -> userJpaEntity.getPostJpaEntities().get(0).getId())
                .collect(Collectors.toList());

        // N+1 문제가 발생합니다. 1(userJpaRepo.findAll()) + 1()번의 쿼리가 실행됩니다.
        for (UserJpaEntity userJpaEntity : userJpaEntities) {
            // UserJpaEntity에 연관된 PostJpaEntity 객체들에 접근
            List<PostJpaEntity> postJpaEntities = userJpaEntity.getPostJpaEntities();

            for (PostJpaEntity postJpaEntity : postJpaEntities) {
                // 여기서 각 PostJpaEntity에 접근할 때마다 별도의 쿼리가 실행됩니다.
                System.out.println("User Id: " + userJpaEntity.getId() + " Post Id: " + postJpaEntity.getId());
            }
        }
    }
}