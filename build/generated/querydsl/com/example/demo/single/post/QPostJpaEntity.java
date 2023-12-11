package com.example.demo.single.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostJpaEntity is a Querydsl query type for PostJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostJpaEntity extends EntityPathBase<PostJpaEntity> {

    private static final long serialVersionUID = 1664502556L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostJpaEntity postJpaEntity = new QPostJpaEntity("postJpaEntity");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final com.example.demo.single.user.QUserJpaEntity userJpaEntity;

    public QPostJpaEntity(String variable) {
        this(PostJpaEntity.class, forVariable(variable), INITS);
    }

    public QPostJpaEntity(Path<? extends PostJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostJpaEntity(PathMetadata metadata, PathInits inits) {
        this(PostJpaEntity.class, metadata, inits);
    }

    public QPostJpaEntity(Class<? extends PostJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userJpaEntity = inits.isInitialized("userJpaEntity") ? new com.example.demo.single.user.QUserJpaEntity(forProperty("userJpaEntity")) : null;
    }

}

