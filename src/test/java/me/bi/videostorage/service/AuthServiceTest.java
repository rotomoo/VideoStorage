package me.bi.videostorage.service;

import me.bi.videostorage.domain.Authority;
import me.bi.videostorage.domain.AuthorityEnum;
import me.bi.videostorage.domain.Member;
import me.bi.videostorage.dto.MemberRequestDto;
import me.bi.videostorage.dto.TokenDto;
import me.bi.videostorage.repository.AuthorityRepository;
import me.bi.videostorage.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    EntityManager em;

    // 테스트 전 데이터 추가
    @BeforeEach
    public void beforeEach() {
        authorityRepository.save(new Authority(AuthorityEnum.ROLE_USER));
        authorityRepository.save(new Authority(AuthorityEnum.ROLE_ADMIN));
    }

    @Test
    public void 회원가입() throws Exception {
        //given
        MemberRequestDto dto = new MemberRequestDto();
        dto.setEmail("test@test.com");
        dto.setPassword("54321");

        //when
        authService.signup(dto);
        // 영속성 컨텍스트 플러쉬
        em.flush();
        em.clear();

        //then
        Optional<Member> byEmail = memberRepository.findByEmail("test@test.com");
        // dto의 이메일, repository에서 찾은 이메일
        assertEquals(dto.getEmail(), byEmail.get().getEmail());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        MemberRequestDto dto1 = new MemberRequestDto();
        dto1.setEmail("User@User.com");
        dto1.setPassword("1234");

        MemberRequestDto dto2 = new MemberRequestDto();
        dto2.setEmail("User@User.com");
        dto2.setPassword("1234");

        //when
        authService.signup(dto1);

        //then
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> authService.signup(dto2));
        assertEquals("이미 가입되어 있는 이메일입니다.", thrown.getMessage());
    }

    @Test
    public void 로그인() throws Exception {
        //given
        MemberRequestDto dto1 = new MemberRequestDto();
        dto1.setEmail("User@User.com");
        dto1.setPassword("1234");
        authService.signup(dto1);

        // 영속성 컨텍스트 플러쉬
        em.flush();
        em.clear();

        MemberRequestDto dto2 = new MemberRequestDto();
        dto2.setEmail("User@User.com");
        dto2.setPassword("1234");

        //when
        TokenDto login = authService.login(dto2);

        //then
        assertEquals(login.getGrantType(), "bearer");
    }
}