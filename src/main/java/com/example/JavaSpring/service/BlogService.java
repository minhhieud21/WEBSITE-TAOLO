package com.example.JavaSpring.service;
import com.example.JavaSpring.models.BlogModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    Page<BlogModel> getAllBlog(Pageable paging);
    Optional<BlogModel> getBlogById(Long id);
    void saveBlog(BlogModel newBlog);
    void deleteBlog(Long id);
    void updateBlog(Long id, BlogModel newBlog);
    List<BlogModel> getBlogByName(String title);
}
