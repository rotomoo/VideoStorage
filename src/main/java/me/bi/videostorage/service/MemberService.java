package me.bi.videostorage.service;

import lombok.RequiredArgsConstructor;
import me.bi.videostorage.domain.Member;
import me.bi.videostorage.dto.MemberResponseDto;
import me.bi.videostorage.dto.MemberUpdateDto;
import me.bi.videostorage.repository.MemberRepository;
import me.bi.videostorage.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 내 정보 조회
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    // 내 정보 수정 (이메일 수정 불가)
    @Transactional
    public void updateMyInfo(MemberUpdateDto dto) {
        Member member = memberRepository
                .findByEmail(getMyInfo().getEmail())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        member.updateMember(dto,passwordEncoder);
    }

/*    // 회원 정보 조회
    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }*/
}