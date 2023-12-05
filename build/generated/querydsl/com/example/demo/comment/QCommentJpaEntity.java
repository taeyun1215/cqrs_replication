package com.example.demo.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentJpaEntity is a Querydsl query type for CommentJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentJpaEntity extends EntityPathBase<CommentJpaEntity> {

    private static final long serialVersionUID = 1307928904L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentJpaEntity commentJpaEntity = new QCommentJpaEntity("commentJpaEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath text = createString("text");

    public final com.example.demo.user.QUserJpaEntity userJpaEntity;

    public QCommentJpaEntity(String variable) {
        this(CommentJpaEntity.class, forVariable(variable), INITS);
    }

    public QCommentJpaEntity(Path<? extends CommentJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentJpaEntity(PathMetadata metadata, PathInits inits) {
        this(CommentJpaEntity.class, metadata, inits);
    }

    public QCommentJpaEntity(Class<? extends CommentJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userJpaEntity = inits.isInitialized("userJpaEntity") ? new com.example.demo.user.QUserJpaEntity(forProperty("userJpaEntity")) : null;
    }

}

