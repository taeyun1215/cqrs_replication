package com.example.demo.book;

import com.example.demo.review.ReviewJpaEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;

    @Builder.Default
    @OneToMany(mappedBy = "bookJpaEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ReviewJpaEntity> reviewJpaEntities = new ArrayList<>();

    public void addReview(ReviewJpaEntity reviewJpaEntity) {
        reviewJpaEntities.add(reviewJpaEntity);
    }

}