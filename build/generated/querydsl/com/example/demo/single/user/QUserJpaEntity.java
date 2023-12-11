package com.example.demo.single.user;

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

    private static final long serialVersionUID = 759645244L;

    public static final QUserJpaEntity userJpaEntity = new QUserJpaEntity("userJpaEntity");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final ListPath<com.example.demo.single.post.PostJpaEntity, com.example.demo.single.post.QPostJpaEntity> postJpaEntities = this.<com.example.demo.single.post.PostJpaEntity, com.example.demo.single.post.QPostJpaEntity>createList("postJpaEntities", com.example.demo.single.post.PostJpaEntity.class, com.example.demo.single.post.QPostJpaEntity.class, PathInits.DIRECT2);

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

