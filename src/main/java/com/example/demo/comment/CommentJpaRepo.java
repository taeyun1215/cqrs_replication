package com.example.demo.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepo extends JpaRepository<CommentJpaEntity, Long> {

}
