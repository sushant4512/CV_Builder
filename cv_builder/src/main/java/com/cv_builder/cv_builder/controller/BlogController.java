package com.cv_builder.cv_builder.controller;

import com.cv_builder.cv_builder.entity.BlogPost;
import com.cv_builder.cv_builder.service.BlogPostService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/blogposts")
public class BlogController {
    @Autowired
    private BlogPostService blogPostService;


    @GetMapping
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
        Optional<BlogPost> blogPost = blogPostService.findById(id);
        return blogPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BlogPost> createBlogPost(@RequestPart("blogPost") BlogPost blogPost,
                                                   @RequestPart("photo") MultipartFile photo) {
            try {
                BlogPost save =blogPostService.save(blogPost, photo);
                return ResponseEntity.ok(save);
            } catch (IOException e) {
                return ResponseEntity.status(500).body(null);
            }

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        if (!blogPostService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        blogPostService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}