package me.bi.videostorage.service;

import lombok.RequiredArgsConstructor;
import me.bi.videostorage.domain.Member;
import me.bi.videostorage.dto.MemberResponseDto;
import me.bi.videostorage.dto.MemberUpdateDto;
import me.bi.videostorage.repository.MemberRepository;
import me.bi.videostorage.repository.RefreshTokenRepository;
import me.bi.videostorage.util.SecurityUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final StringRedisTemplate redisTemplate;

    /**
     * 내 정보 조회
     */
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    /**
     * 내 정보 수정 (이메일 수정 불가)
     */
    @Transactional
    public void updateMyInfo(MemberUpdateDto dto) {
        Member member = memberRepository
                .findByEmail(getMyInfo().getEmail())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        member.updateMember(dto,passwordEncoder);
    }

    /**
     * 로그아웃
     */
    @Transactional
    public void logout(HttpServletRequest request) {

        // accessToken redisTemplate 블랙리스트 추가
        String jwt = request.getHeader("Authorization").substring(7);
        ValueOperations<String, String> logoutValueOperations = redisTemplate.opsForValue();
        logoutValueOperations.set(jwt, jwt); // redis set 명령어

        // refreshToken 삭제
        refreshTokenRepository.deleteByKey(String.valueOf(SecurityUtil.getCurrentMemberId()))
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public void deleteMember() {
        memberRepository.deleteByEmail(getMyInfo().getEmail())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }
}