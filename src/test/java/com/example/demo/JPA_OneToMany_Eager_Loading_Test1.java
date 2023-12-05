package com.example.demo;

import com.example.demo.comment.CommentJpaEntity;
import com.example.demo.comment.CommentJpaRepo;
import com.example.demo.user.UserJpaEntity;
import com.example.demo.user.UserJpaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@SpringBootTest
@DisplayName("1:N 관계에서 N에서 EAGER Loading 을 사용할 때 발생하는 N+1 문제를 확인합니다.")
public class JPA_OneToMany_Eager_Loading_Test1 {

    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private CommentJpaRepo commentJpaRepo;

    @Autowired
    EntityManager em;

    @BeforeEach
    public void setup() {
        for (int i = 1; i <= 10; i++) {
            // UserJpaEntity 객체 생성
            UserJpaEntity userJpaEntity = UserJpaEntity.builder()
                    .email("user" + "@example.com")
                    .password("password")
                    .nickname("nickname")
                    .name("User ")
                    .build();

            // CommentJpaEntity 객체 생성
            CommentJpaEntity commentJpaEntity1 = CommentJpaEntity.builder()
                    .text("text " + i)
                    .build();

            CommentJpaEntity commentJpaEntity2 = CommentJpaEntity.builder()
                    .text("text " + i)
                    .build();

            // UserJpaEntity에 CommentJpaEntity를 추가
            userJpaEntity.addComment(commentJpaEntity1);
            userJpaEntity.addComment(commentJpaEntity2);

            // CommentJpaEntity에 UserJpaEntity를 추가
            commentJpaEntity1.addUser(userJpaEntity);
            commentJpaEntity2.addUser(userJpaEntity);

            // UserJpaEntity를 저장 (CascadeType.ALL에 의해 CommentJpaEntity더 저장됩니다)
            userJpaRepo.save(userJpaEntity);
        }
    }

    @Test
    public void JPA_OneToMany_Eager_Loading_Test1() {
        em.flush();
        em.clear();
        System.out.println("------------ 영속성 컨텍스트 비우기 -----------\n");

        System.out.println("------------ USER 전체 조회 요청 ------------");
        List<UserJpaEntity> userJpaEntities = userJpaRepo.findAll();
        System.out.println("------------ USER 전체 조회 완료. [조회된 USER의 개수(N=10) 만큼 추가적인 쿼리 발생]------------\n\n");

        System.out.println("------------ USER ID 조회 요청 ------------");
        userJpaEntities.forEach(userJpaEntity -> System.out.println("USER ID: " + userJpaEntity.getId()));
        System.out.println("------------ USER ID 조회 완료. [추가적인 쿼리 발생하지 않음]------------\n\n");

        System.out.println("------------ USER와 연관관계인 Comment 내용 조회 요청------------");
        userJpaEntities.forEach(userJpaEntity -> System.out.println("USER IN Comment ID: " + userJpaEntity.getCommentJpaEntities().get(0).getId()));
        System.out.println("------------ USER와 연관관계인 Comment 내용 조회 완료 [추가적인 쿼리 발생하지 않음] ------------\n\n");
    }
}