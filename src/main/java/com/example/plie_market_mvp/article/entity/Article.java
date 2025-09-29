package com.example.plie_market_mvp.article.entity;

import com.example.plie_market_mvp.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "article")
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Condition condition;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TradeMethod tradeMethod;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private int viewCount = 0;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void increaseViewCount() {
        this.viewCount++;
    }
    // ✨ 빌더는 이 생성자 기준으로만 만들어짐
    @Builder
    public Article(String title, int price, Category category, Condition condition,
                TradeMethod tradeMethod, String imageUrl, String productName,
                String size, String color, User user) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.condition = condition;
        this.tradeMethod = tradeMethod;
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public void update(String title, int price, Category category, //나중에 수정할 때 쓰려고...?
                       Condition condition, TradeMethod tradeMethod,
                       String imageUrl, String productName, String size, String color) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.condition = condition;
        this.tradeMethod = tradeMethod;
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.updatedAt = LocalDateTime.now();
    }


}
