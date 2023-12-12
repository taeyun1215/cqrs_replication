package com.example.demo.multiple;

import com.example.demo.multiple.category.Category;
import com.example.demo.multiple.category.CategoryRepo;
import com.example.demo.multiple.category.QCategory;
import com.example.demo.multiple.customer.Customer;
import com.example.demo.multiple.customer.CustomerRepo;
import com.example.demo.multiple.customer.QCustomer;
import com.example.demo.multiple.product.*;
import com.example.demo.multiple.review.QReview;
import com.example.demo.multiple.review.Review;
import com.example.demo.multiple.review.ReviewRepo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@SpringBootTest
public class Multiple_QueryDSL_OneToMany_Lazy_Loding_Test {

    @Autowired
    private EntityManager em;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private CustomerRepo customerRepo;

    QProduct product = QProduct.product;
    QCategory category = QCategory.category;
    QReview review = QReview.review;
    QCustomer customer = QCustomer.customer;

    @BeforeEach
    void setup() {
//        for (int i = 0; i < 2; i++) {
//            Category category = Category.builder()
//                    .name("category_name" + i)
//                    .build();
//            categoryRepo.save(category);
//
//            for (int j = 0; j < 2; j++) {
//                Product product = Product.builder()
//                        .name("product_name" + j)
//                        .price(10.0)
//                        .category(category)
//                        .build();
//                productRepo.save(product);
//
//                for (int k = 0; k < 2; k++) {
//                    Customer customer = Customer.builder()
//                            .name("customer_name" + k)
//                            .email("customer_email" + k)
//                            .build();
//                    customerRepo.save(customer);
//
//                    for (int l = 0; l < 2; l++) {
//                        Review review = Review.builder()
//                                .content("review_content" + l)
//                                .rating(5)
//                                .product(product)
//                                .customer(customer)
//                                .build();
//                        reviewRepo.save(review);
//                    }
//                }
//            }
//        }
    }

    @Test
    @DisplayName("QueryDSL과 Fetch Join을 사용하여 N+1 문제 해결")
    public void testFetchJoinWithQueryDSL1() {
        em.flush();
        em.clear();
        System.out.println("------------ 영속성 컨텍스트 비우기 -----------\n");

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<Product> products = queryFactory
                .selectFrom(product)
                .fetch();

        for (Product p : products) {
            System.out.println("Product: " + p.getName());
            System.out.println("Category: " + p.getCategory().getName());
            for (Review r : p.getReviews()) {
                System.out.println("Review: " + r.getContent());
                System.out.println("Customer: " + r.getCustomer().getName());
            }
        }
    }

    @Test
    @DisplayName("QueryDSL과 Fetch Join을 사용하여 N+1 문제 해결")
    public void testFetchJoinWithQueryDSL2() {
        em.flush();
        em.clear();
        System.out.println("------------ 영속성 컨텍스트 비우기 -----------\n");

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<Product> products = queryFactory
                .selectFrom(product)
                .leftJoin(product.reviews, review).fetchJoin()
                .leftJoin(product.category, category).fetchJoin()
                .fetch();

        for (Product p : products) {
            System.out.println("Product: " + p.getName());
            System.out.println("Category: " + p.getCategory().getName());
            for (Review r : p.getReviews()) {
                System.out.println("Review: " + r.getContent());
                System.out.println("Customer: " + r.getCustomer().getName());
            }
        }
    }

    @Test
    @DisplayName("Product 안에 category 데이터 다 가져오기")
    public void testFetchJoinWithQueryDSL3() {
        em.flush();
        em.clear();
        System.out.println("------------ 영속성 컨텍스트 비우기 -----------\n");

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        queryFactory
                .select(Projections.fields(ProductDetails1.class,
                        product.id,
                        product.name,
                        product.price,
                        category))
                .from(product)
                .fetch();
    }

    @Test
    @DisplayName("Product 안에 category Id 만 가져오기")
    public void testFetchJoinWithQueryDSL4() {
        em.flush();
        em.clear();
        System.out.println("------------ 영속성 컨텍스트 비우기 -----------\n");

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QProduct product = QProduct.product;
        QCategory category = QCategory.category;

        queryFactory
                .select(Projections.fields(ProductDetails2.class,
                        product.id,
                        product.name,
                        product.price,
                        category.id))
                .from(product)
                .fetch();

    }
}
