package me.bi.videostorage.controller;

import lombok.RequiredArgsConstructor;
import me.bi.videostorage.dto.MemberResponseDto;
import me.bi.videostorage.dto.MemberUpdateDto;
import me.bi.videostorage.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@PreAuthorize("isAuthenticated()")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @PutMapping("/update")
    public ResponseEntity<MemberResponseDto> updateMyInfo(@RequestBody MemberUpdateDto dto) {
        memberService.updateMyInfo(dto);
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMember(HttpServletRequest request) {
        memberService.logout(request);
        memberService.deleteMember();
        return new ResponseEntity<>("회원 탈퇴 성공", HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        memberService.logout(request);
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}