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

## ▶ Postman Test
https://user-images.githubusercontent.com/30739181/204204334-94f852dd-5608-46be-bb17-3cdee03a3f83.mp4
