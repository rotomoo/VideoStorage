[스프링부트] REST API VideoStorage
======================
# 1. 웹 설명
## ▶ 웹 설명
	1. 개발 환경 - Spring Boot 2.6.8, java 11, (DB)Mysql
    2. 핵심 라이브러리 - Spring Web, Spring Security, JPA, Mysql, jjwt 0.11.2
    3. 기타 라이브러리 - lombok, validation, devtools, Test
    4. Security, Jwt(access, refresh Token) 기반 회원가입, 로그인, 권한별 비디오 업로드, 재생

****
# 2. 개발 내용
## ▶ 기능
- 유저 관리
    - 회원 가입
    
- 유저 로그인
    - 토큰 방식으로 구현
        - Spring Securit 사용, access token, refresh token 구현
        
- 비디오 파일 업로드 (진행중)
    - **(권한: user 권한만 사용 가능)**
    - 로컬 디스크에 저장
    - 최대 100MB 까지 허용
    
- 비디오 파일 재생 (진행중)
    - **(권한: 비디오 소유자, admin 권한만 접근 가능)**
    - 스트림 방식으로 요청, 206 Partial Download 구현
    - 재생 검증 html video tag 사용

전체 기능은 테스트 코드를 통해 기능 검증 (진행중)

****