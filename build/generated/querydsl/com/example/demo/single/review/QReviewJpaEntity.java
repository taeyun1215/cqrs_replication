package com.example.demo.single.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewJpaEntity is a Querydsl query type for ReviewJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewJpaEntity extends EntityPathBase<ReviewJpaEntity> {

    private static final long serialVersionUID = 970323868L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewJpaEntity reviewJpaEntity = new QReviewJpaEntity("reviewJpaEntity");

    public final com.example.demo.single.book.QBookJpaEntity bookJpaEntity;

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QReviewJpaEntity(String variable) {
        this(ReviewJpaEntity.class, forVariable(variable), INITS);
    }

    public QReviewJpaEntity(Path<? extends ReviewJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewJpaEntity(PathMetadata metadata, PathInits inits) {
        this(ReviewJpaEntity.class, metadata, inits);
    }

    public QReviewJpaEntity(Class<? extends ReviewJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bookJpaEntity = inits.isInitialized("bookJpaEntity") ? new com.example.demo.single.book.QBookJpaEntity(forProperty("bookJpaEntity")) : null;
    }

}

