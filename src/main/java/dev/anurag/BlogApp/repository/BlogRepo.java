package dev.anurag.BlogApp.repository;

import dev.anurag.BlogApp.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepo extends JpaRepository<BlogEntity, Long> {
}
