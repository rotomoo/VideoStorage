package me.bi.videostorage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.bi.videostorage.domain.Member;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String email;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail());
    }
}