package com.example.plie_market_mvp.article.controller;

import com.example.plie_market_mvp.article.dto.ArticleRequest;
import com.example.plie_market_mvp.article.dto.ArticleResponse;
import com.example.plie_market_mvp.article.service.ArticleService;
import com.example.plie_market_mvp.common.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final JwtTokenProvider jwtTokenProvider;

    // 글 작성
    @PostMapping
    public ResponseEntity<ArticleResponse> createArticle(
            @RequestBody ArticleRequest request,
            @RequestHeader("Authorization") String token
    ) {
        String email = jwtTokenProvider.getEmail(token.substring(7));
        return ResponseEntity.ok(articleService.createArticle(request, email));
    }

    // 글 한 건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    // 글 전체 조회
    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

    // 글 수정
    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(
            @PathVariable Long id,
            @RequestBody ArticleRequest request,
            @RequestHeader("Authorization") String token
    ) {
        String email = jwtTokenProvider.getEmail(token.substring(7));
        return ResponseEntity.ok(articleService.updateArticle(id, request, email));
    }

    // 글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token
    ) {
        String email = jwtTokenProvider.getEmail(token.substring(7));
        articleService.deleteArticle(id, email);
        return ResponseEntity.ok("Article deleted");
    }
}
