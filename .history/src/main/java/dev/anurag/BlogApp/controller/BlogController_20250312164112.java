package dev.anurag.BlogApp.controller;


import dev.anurag.BlogApp.entity.BlogEntity;
import dev.anurag.BlogApp.service.AiIntegrationService;
import dev.anurag.BlogApp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final AiIntegrationService aiIntegrationService;

    @PostMapping
    public ResponseEntity<BlogEntity> createBlog(@RequestBody BlogEntity blog) {
        return new ResponseEntity<>(blogService.createBlog(blog), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BlogEntity>> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size) {
        List<BlogEntity> allBlogs = blogService.getAllBlogs(PageRequest.of(page,size));
        if(!allBlogs.isEmpty()){
            return new ResponseEntity<>(allBlogs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long id) {
        Optional<BlogEntity> blog = blogService.getBlogById(id);
        if(blog.isPresent()){
            return new ResponseEntity<>(blog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@RequestBody BlogEntity updatedBlog, @PathVariable Long id) {
        BlogEntity blogInDb = blogService.getBlogById(id).orElse(null);
        if (blogInDb != null) {
            blogInDb.setTitle(updatedBlog.getTitle());
            blogInDb.setContent(updatedBlog.getContent());
            blogService.createBlog(blogInDb);
            return new ResponseEntity<>(blogInDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<?> summary(@PathVariable Long id){
        Optional<BlogEntity> getBlog = blogService.getBlogById(id);
        if(getBlog.isPresent()){
            String summary = aiIntegrationService.generateSummary(getBlog.get().getContent());
            return new ResponseEntity<>(summary, HttpStatus.OK);
        }
        else{
            if(getBlog.isEmpty()) {
                return new ResponseEntity<>("No Blog available for id " + id, HttpStatus.NO_CONTENT);
            }
        }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}