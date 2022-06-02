package me.bi.videostorage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.bi.videostorage.domain.Authority;
import me.bi.videostorage.domain.Member;
import me.bi.videostorage.domain.Video;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Getter
@NoArgsConstructor
public class VideoRequestDto {
    private Long id;
    private Member member;
    private String origFileName;
    private String filePath;

    public Video toEntity() {
        return Video.builder()
                .id(id)
                .member(member)
                .origFileName(origFileName)
                .filePath(filePath)
                .build();
    }

    @Builder
    public VideoRequestDto(Long id, Member member, String origFileName, String filePath) {
        this.id = id;
        this.member = member;
        this.origFileName = origFileName;
        this.filePath = filePath;
    }
}