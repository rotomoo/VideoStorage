package me.bi.videostorage.service;

import lombok.RequiredArgsConstructor;
import me.bi.videostorage.domain.Video;
import me.bi.videostorage.dto.VideoRequestDto;
import me.bi.videostorage.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    /**
     * 비디오 업로드
     */
    @Transactional
    public Long videoUpload(VideoRequestDto videoRequestDto) {
        return videoRepository.save(videoRequestDto.toEntity()).getId();
    }

    /**
     * 비디오 정보 가져오기
     */
    @Transactional
    public VideoRequestDto getVideo(Long id) {
        Video video = videoRepository.findById(id).get();

        VideoRequestDto videoRequestDto = VideoRequestDto.builder()
                .id(id)
                .member(video.getMember())
                .origFileName(video.getOrigFileName())
                .filePath(video.getFilePath())
                .build();
        return videoRequestDto;
    }

}
