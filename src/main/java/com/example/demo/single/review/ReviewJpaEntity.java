package com.example.demo.single.review;

import com.example.demo.single.book.BookJpaEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "reviews111")
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
