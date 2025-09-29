package com.example.plie_market_mvp.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter//클라로 내보냄
@AllArgsConstructor //응답 만들 때 모든 필드를 한번에 생성함.
public class ArticleResponse {//GET(조회)//서버->클라
    private Long id;
    private String title;
    private int price;
    private String category;
    private String condition;
    private String tradeMethod;
    private String imageUrl;
    private String productName;
    private String size;
    private String color;
    private int viewCount;
    private String nickname;  // 작성자 닉네임. 1:다 fk
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}