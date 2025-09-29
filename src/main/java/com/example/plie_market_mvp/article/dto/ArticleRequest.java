package com.example.plie_market_mvp.article.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter//service에서 읽어야함.
@NoArgsConstructor//JSON → DTO 변환할 때(JSON 역직렬화)(Spring MVC에서 @RequestBody 로 받을 때) 기본 생성자가 반드시 필요
public class ArticleRequest { //form 입력값(POST) 주로 POST/PUT/PATCH에 사용.
    private String title;
    private int price;
    private String category;
    private String condition;
    private String tradeMethod;
    private String imageUrl;
    private String productName;
    private String size;
    private String color;
}