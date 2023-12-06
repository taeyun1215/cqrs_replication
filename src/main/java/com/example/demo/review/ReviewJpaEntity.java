package com.example.demo.review;

import com.example.demo.book.BookJpaEntity;
import com.example.demo.user.UserJpaEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private BookJpaEntity bookJpaEntity;

    public void addBook(BookJpaEntity bookJpaEntity) {
        this.bookJpaEntity = bookJpaEntity;
    }
}
