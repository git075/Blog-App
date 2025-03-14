package dev.anurag.BlogApp.service;

import dev.anurag.BlogApp.entity.BlogEntity;
import dev.anurag.BlogApp.repository.BlogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepo blogRepository;

    public BlogEntity createBlog(BlogEntity blog) {
        return blogRepository.save(blog);
    }

    public List<BlogEntity> getAllBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable).getContent();
    }

    public Optional<BlogEntity> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public BlogEntity updateBlog(Long id, BlogEntity updatedBlog) {
        return blogRepository.findById(id).map(blog -> {
            blog.setTitle(updatedBlog.getTitle());
            blog.setContent(updatedBlog.getContent());
            return blogRepository.save(blog);
        }).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}