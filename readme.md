[스프링부트, JWT] VideoStorage - REST API
======================
# 1. 웹 설명
## ▶ 웹 설명
	1. 개발 환경 - Spring Boot 2.6.8, java 11, (DB)Mysql, (DB)Redis
    2. 핵심 라이브러리 - Spring Web, Spring Security, JPA, Mysql, redis, jjwt 0.11.2
    3. 기타 라이브러리 - lombok, validation, devtools, Test
    4. Security, Jwt(access, refresh Token) 기반 회원가입, 로그인, 권한별 비디오 업로드, 재생

****
# 2. 개발 내용
## ▶ 기능
- 회원 기능
    - 회원 가입
    - 로그인
    - 로그아웃
    - 회원 탈퇴
    - 토큰 재발급
    - 내 정보 조회
    - 내 정보 수정
    
- 비디오 기능
    - 비디오 파일 업로드 - user 권한
    - 비디오 파일 재생 - 업로더, admin 권한
    - 비디오 리스트 조회

전체 기능은 POST MAN을 통해 기능 검증

****
# 3. 개발 순서
1. 프로젝트 환경 설정 [blog](https://rotomoo.tistory.com/65?category=1006036)
2. JWT 소개, 도메인 분석 설계 [blog](https://rotomoo.tistory.com/66?category=1006036)
3. 엔티티 클래스 개발 [blog](https://rotomoo.tistory.com/67?category=1006036)
4. JWT, Security 개발 [blog](https://rotomoo.tistory.com/68?category=1006036)
5. 레포지토리 계층 , initDB class 개발 [blog](https://rotomoo.tistory.com/69?category=1006036)
6. 서비스 계층 개발 - 1. 회원가입, 로그인, 재발급 [blog](https://rotomoo.tistory.com/70?category=1006036)
7. 서비스 계층 개발 - 2. 내 정보 조회, 수정, 로그아웃, 회원 탈퇴 [blog](https://rotomoo.tistory.com/71?category=1006036)
8. 서비스 계층 개발 - 3. 비디오 업로드, 비디오 재생, 비디오 리스트 조회 [blog](https://rotomoo.tistory.com/72?category=1006036)
9. 웹 계층 개발 - 1. 회원가입, 로그인, 재발급 [blog](https://rotomoo.tistory.com/73?category=1006036)
10. 웹 계층 개발 - 2. 내 정보 조회, 수정, 로그아웃, 회원 탈퇴 [blog](https://rotomoo.tistory.com/74?category=1006036)
11. 웹 계층 개발 - 3. 비디오 업로드, 비디오 리스트 조회, 비디오 재생 [blog](https://rotomoo.tistory.com/75?category=1006036)