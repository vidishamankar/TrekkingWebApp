package com.treksafe.treksafe.repository;

import com.treksafe.treksafe.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the Blog entity.
 * Provides standard CRUD operations for blog posts.
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    // Custom query to find all posts by a specific category, ordered by date
    List<Blog> findByCategoryOrderByPublishedDateDesc(String category);

    // Custom query to find a post by title
    Optional<Blog> findByTitle(String title);
}
