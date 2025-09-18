# 발레 중고 거래 플랫폼 Plie Market (MVP 버전)
## 서비스 바로 가기
## 서비스 소개

## 기획 의도

## 서비스 기능
- 회원가입/로그인 (JWT 인증)
- 상품 등록/조회/수정/삭제 (이미지 업로드)
- 댓글 작성/수정/삭제
- 상품 검색
- 정렬 (최신순, 조회순)
- 카테고리별 조회

## 트러블슈팅

## 기술 스택
- Language: Java 17
- Framework: Spring Boot 3, Spring Data JPA, Spring Security (JWT)
- DB: MySQL
- View: Mustache
- Deploy: AWS EC2

## ERD

## 시스템 아키텍쳐

## 프로젝트 구조
<pre>
  <code>
plie-market-mvp/
┣ .gitignore
┣ build.gradle
┣ settings.gradle
┣ README.md
┣ src/
┃ ┣ main/
┃ ┃ ┣ java/com/example/plie_market_mvp/
┃ ┃ ┃ ┣ PlieMarketMvpApplication.java   # 메인 실행 클래스
┃ ┃ ┃ ┣ config/                      
┃ ┃ ┃ ┣ controller/               
┃ ┃ ┃ ┣ dto/                           
┃ ┃ ┃ ┣ entity/                      
┃ ┃ ┃ ┣ repository/                  
┃ ┃ ┃ ┣ service/                    
┃ ┃ ┃ ┗ exception/                 
┃ ┃ ┗ resources/
┃ ┃ ┃ ┣ application.yml              
┃ ┃ ┃ ┣ static/                      
┃ ┃ ┃ ┗ templates/            
┃ ┗ test/java/com/example/plie_market_mvp/ 
┗ gradlew, gradlew.bat
┗ gradle/
  </code>
</pre>


## API 명세서

## 개발 기간
-2025.09.18~2025.10.05





