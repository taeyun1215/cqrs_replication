package com.example.demo.user.adapter.out.persistence;

import com.example.demo.post.PostJpaEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "userJpaEntity", cascade = CascadeType.ALL)
    private List<PostJpaEntity> postJpaEntities = new ArrayList<>();

    public void addPost(PostJpaEntity postJpaEntity) {
        postJpaEntities.add(postJpaEntity);
    }
}
