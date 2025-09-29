package com.example.plie_market_mvp.article.repository;

import com.example.plie_market_mvp.article.entity.Article;
import com.example.plie_market_mvp.article.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategory(Category category);
    List<Article> findByTitleContainingIgnoreCase(String keyword);
}
