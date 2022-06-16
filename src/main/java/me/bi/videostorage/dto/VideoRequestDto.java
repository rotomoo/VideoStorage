package me.bi.videostorage.dto;

import lombok.*;
import me.bi.videostorage.domain.Authority;
import me.bi.videostorage.domain.Member;
import me.bi.videostorage.domain.Video;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Data
public class VideoRequestDto {
    private Member member;
    private String origFileName;
    private String filePath;

    public Video toEntity() {
        return Video.builder()
                .member(member)
                .origFileName(origFileName)
                .filePath(filePath)
                .build();
    }

    @Builder
    public VideoRequestDto(Member member, String origFileName, String filePath) {
        this.member = member;
        this.origFileName = origFileName;
        this.filePath = filePath;
    }
}