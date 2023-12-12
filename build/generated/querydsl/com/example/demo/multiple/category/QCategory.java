package com.example.demo.multiple.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = 574176938L;

    public static final QCategory category = new QCategory("category");

    public final StringPath description1 = createString("description1");

    public final StringPath description2 = createString("description2");

    public final StringPath description3 = createString("description3");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<com.example.demo.multiple.product.Product, com.example.demo.multiple.product.QProduct> products = this.<com.example.demo.multiple.product.Product, com.example.demo.multiple.product.QProduct>createList("products", com.example.demo.multiple.product.Product.class, com.example.demo.multiple.product.QProduct.class, PathInits.DIRECT2);

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

