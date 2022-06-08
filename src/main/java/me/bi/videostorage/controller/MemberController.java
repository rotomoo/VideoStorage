package me.bi.videostorage.controller;

import lombok.RequiredArgsConstructor;
import me.bi.videostorage.dto.MemberResponseDto;
import me.bi.videostorage.dto.MemberUpdateDto;
import me.bi.videostorage.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getCurrentInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @PutMapping("/update")
    public void updateMember(@RequestBody MemberUpdateDto dto) {
        memberService.updateMemberInfo(dto);
    }
}