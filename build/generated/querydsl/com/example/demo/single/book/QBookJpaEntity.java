package com.example.demo.single.book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookJpaEntity is a Querydsl query type for BookJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookJpaEntity extends EntityPathBase<BookJpaEntity> {

    private static final long serialVersionUID = -18114180L;

    public static final QBookJpaEntity bookJpaEntity = new QBookJpaEntity("bookJpaEntity");

    public final StringPath author = createString("author");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.example.demo.single.review.ReviewJpaEntity, com.example.demo.single.review.QReviewJpaEntity> reviewJpaEntities = this.<com.example.demo.single.review.ReviewJpaEntity, com.example.demo.single.review.QReviewJpaEntity>createList("reviewJpaEntities", com.example.demo.single.review.ReviewJpaEntity.class, com.example.demo.single.review.QReviewJpaEntity.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QBookJpaEntity(String variable) {
        super(BookJpaEntity.class, forVariable(variable));
    }

    public QBookJpaEntity(Path<? extends BookJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookJpaEntity(PathMetadata metadata) {
        super(BookJpaEntity.class, metadata);
    }

}

