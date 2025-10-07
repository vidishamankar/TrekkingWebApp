package com.treksafe.treksafe.controller;

import com.treksafe.treksafe.model.Blog;
import com.treksafe.treksafe.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for handling Blog post API requests.
 * It uses the BlogService to fetch blog data.
 */
@RestController
@RequestMapping("/api/v1/blog") // Base URL for blog posts
public class BlogController {

    private final BlogService blogService;

    // Constructor Injection
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Handles GET requests to /api/v1/blog
     * Returns a list of all available blog posts, ordered by date.
     *
     * @return A list of Blog objects.
     */
    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogPosts() {
        List<Blog> posts = blogService.findAllPosts();
        return ResponseEntity.ok(posts);
    }

    /**
     * Handles GET requests to /api/v1/blog/{id}
     * Returns a specific blog post by its ID.
     *
     * @param id The ID of the blog post.
     * @return The Blog object or 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogPostById(@PathVariable Long id) {
        return blogService.findPostById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
