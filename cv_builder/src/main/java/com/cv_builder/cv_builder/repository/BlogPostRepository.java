package com.cv_builder.cv_builder.repository;

import com.cv_builder.cv_builder.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

}
