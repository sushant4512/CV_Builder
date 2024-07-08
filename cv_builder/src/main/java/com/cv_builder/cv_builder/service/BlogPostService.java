package com.cv_builder.cv_builder.service;

import com.cv_builder.cv_builder.entity.BlogPost;

import com.cv_builder.cv_builder.repository.BlogPostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> findById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost save(BlogPost blogPost, MultipartFile photo) throws IOException {
        blogPost.setPhoto(photo.getResource().getFilename());
        return blogPostRepository.save(blogPost);
    }

    public void deleteById(Long id) {
        blogPostRepository.deleteById(id);
    }
}