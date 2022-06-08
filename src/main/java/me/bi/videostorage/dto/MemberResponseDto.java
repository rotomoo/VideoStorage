package me.bi.videostorage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.bi.videostorage.domain.Authority;
import me.bi.videostorage.domain.Member;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String email;
    private String memberName;
    private String phone;
    private Set<Authority> authorities;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail(), member.getMemberName(), member.getPhone(), member.getAuthorities());
    }
}