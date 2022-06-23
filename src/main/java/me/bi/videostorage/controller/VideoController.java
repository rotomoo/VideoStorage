package me.bi.videostorage.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.bi.videostorage.domain.Video;
import me.bi.videostorage.dto.VideoListResponseDto;
import me.bi.videostorage.dto.VideoListDto;
import me.bi.videostorage.service.VideoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class VideoController {

    @Value("${video.upload.location}")
    private String dir;

    private final VideoService videoService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> uploadVideo(MultipartFile file) throws IOException {
        videoService.uploadVideo(file);
        return new ResponseEntity<>("업로드 성공", HttpStatus.OK);
    }

//    @GetMapping("/play")
//    public ResponseEntity<ResourceRegion> playVideo(@RequestHeader HttpHeaders httpHeaders, VideoPlayDto VideoPlayDto) throws IOException {
//        FileUrlResource resource = new FileUrlResource("C:\\Users\\bluesky\\Downloads\\ForBiggerBlazes.mp4");
//        ResourceRegion resourceRegion = resourceRegion(resource, httpHeaders);
//        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
//                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
//                .body(resourceRegion);
//    }

    @GetMapping("/list")
    public VideoListResponseDto findVideos() {
        List<Video> videos = videoService.findVideos();
        List<VideoListDto> collect = videos.stream()
                .map(v -> new VideoListDto(v.getOrigFileName()))
                .collect(Collectors.toList());

        return new VideoListResponseDto(collect.size(), collect);
    }
}