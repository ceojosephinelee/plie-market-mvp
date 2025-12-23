package com.example.plie_market_mvp.article.service;

import com.example.plie_market_mvp.article.dto.ArticleRequest;
import com.example.plie_market_mvp.article.dto.ArticleResponse;
import com.example.plie_market_mvp.article.entity.Article;
import com.example.plie_market_mvp.article.entity.Category;
import com.example.plie_market_mvp.article.entity.Condition;
import com.example.plie_market_mvp.article.entity.TradeMethod;
import com.example.plie_market_mvp.article.repository.ArticleRepository;
import com.example.plie_market_mvp.user.entity.User;
import com.example.plie_market_mvp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //final 또는 @NonNull이 붙은 필드 생성자 자동으로 만들어 줌.필수 의존성을 생성자 주입 방식으로 넣을 수 있게 해줌.
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    // 글 작성
    public ArticleResponse createArticle(ArticleRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Article article = Article.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .category(Category.valueOf(request.getCategory()))
                .condition(Condition.valueOf(request.getCondition()))
                .tradeMethod(TradeMethod.valueOf(request.getTradeMethod()))
                .imageUrl(request.getImageUrl())
                .productName(request.getProductName())
                .size(request.getSize())
                .color(request.getColor())
                .user(user)
                .build();

        articleRepository.save(article);

        return toResponse(article); //Entity를 dto로 변환!
    }

    // 글 한 건 조회 (조회수 증가 포함)
    public ArticleResponse getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        article.increaseViewCount();
        articleRepository.save(article);
        return toResponse(article);
    }

    // 글 전체 조회
    public List<ArticleResponse> getArticles() {
        return articleRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    // 글 수정
    public ArticleResponse updateArticle(Long id, ArticleRequest request, String email) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!article.getUser().getEmail().equals(email)) {
            throw new RuntimeException("No permission to edit");
        }

        article.update(
                request.getTitle(),
                request.getPrice(),
                Category.valueOf(request.getCategory()),
                Condition.valueOf(request.getCondition()),
                TradeMethod.valueOf(request.getTradeMethod()),
                request.getImageUrl(),
                request.getProductName(),
                request.getSize(),
                request.getColor()
        );
        articleRepository.save(article);

        return toResponse(article);
    }

    // 글 삭제
    public void deleteArticle(Long id, String email) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        if (!article.getUser().getEmail().equals(email)) {
            throw new RuntimeException("No permission to delete");
        }
        articleRepository.delete(article);
    }

    private ArticleResponse toResponse(Article article) { //entity를 dto로 변환!
        return new ArticleResponse(
                article.getArticleId(),
                article.getTitle(),
                article.getPrice(),
                article.getCategory().name(),
                article.getCondition().name(),
                article.getTradeMethod().name(),
                article.getImageUrl(),
                article.getProductName(),
                article.getSize(),
                article.getColor(),
                article.getViewCount(),
                article.getUser().getNickname(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );
    }
}
