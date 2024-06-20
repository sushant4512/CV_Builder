package com.cv_builder.cv_builder.controller;

import com.cv_builder.cv_builder.entity.BlogPost;
import com.cv_builder.cv_builder.entity.Photo;
import com.cv_builder.cv_builder.service.BlogPostService;
import com.cv_builder.cv_builder.service.PhotoService;
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

    @Autowired
    private PhotoService photoService;

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
                                                   @RequestPart("photos") MultipartFile[] photos) {
        BlogPost savedBlogPost = blogPostService.save(blogPost);
        for (MultipartFile file : photos) {
            Photo photo = new Photo();
            photo.setFilename(file.getOriginalFilename());
            try {
                photo.setData(file.getBytes());
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            photo.setBlogPost(savedBlogPost);
            photoService.save(photo);
        }
        return new ResponseEntity<>(savedBlogPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id,
                                                   @RequestPart("blogPost") BlogPost blogPost,
                                                   @RequestPart("photos") MultipartFile[] photos) {
        if (!blogPostService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        blogPost.setId(id);
        BlogPost updatedBlogPost = blogPostService.save(blogPost);
        for (MultipartFile file : photos) {
            Photo photo = new Photo();
            photo.setFilename(file.getOriginalFilename());
            try {
                photo.setData(file.getBytes());
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            photo.setBlogPost(updatedBlogPost);
            photoService.save(photo);
        }
        return ResponseEntity.ok(updatedBlogPost);
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