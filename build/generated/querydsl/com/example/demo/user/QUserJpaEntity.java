package com.example.demo.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserJpaEntity is a Querydsl query type for UserJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserJpaEntity extends EntityPathBase<UserJpaEntity> {

    private static final long serialVersionUID = -594256886L;

    public static final QUserJpaEntity userJpaEntity = new QUserJpaEntity("userJpaEntity");

    public final ListPath<com.example.demo.comment.CommentJpaEntity, com.example.demo.comment.QCommentJpaEntity> commentJpaEntities = this.<com.example.demo.comment.CommentJpaEntity, com.example.demo.comment.QCommentJpaEntity>createList("commentJpaEntities", com.example.demo.comment.CommentJpaEntity.class, com.example.demo.comment.QCommentJpaEntity.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final ListPath<com.example.demo.post.PostJpaEntity, com.example.demo.post.QPostJpaEntity> postJpaEntities = this.<com.example.demo.post.PostJpaEntity, com.example.demo.post.QPostJpaEntity>createList("postJpaEntities", com.example.demo.post.PostJpaEntity.class, com.example.demo.post.QPostJpaEntity.class, PathInits.DIRECT2);

    public QUserJpaEntity(String variable) {
        super(UserJpaEntity.class, forVariable(variable));
    }

    public QUserJpaEntity(Path<? extends UserJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserJpaEntity(PathMetadata metadata) {
        super(UserJpaEntity.class, metadata);
    }

}

