package me.bi.videostorage.service;

import lombok.RequiredArgsConstructor;
import me.bi.videostorage.domain.Authority;
import me.bi.videostorage.domain.Member;
import me.bi.videostorage.domain.Video;
import me.bi.videostorage.dto.VideoPlayRequestDto;
import me.bi.videostorage.repository.MemberRepository;
import me.bi.videostorage.repository.VideoRepository;
import me.bi.videostorage.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VideoService {

    @Value("${video.upload.location}")
    private String dir;

    private final MemberRepository memberRepository;
    private final VideoRepository videoRepository;


    /**
     * 비디오 업로드
     */
    @Transactional
    public void uploadVideo(MultipartFile multipartFile) throws IOException {

        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        String videoName = multipartFile.getOriginalFilename();

        Path location = Paths.get(dir).resolve(videoName);

        Files.copy(multipartFile.getInputStream(), location, StandardCopyOption.REPLACE_EXISTING);

        Video video = Video.builder()
                .member(member)
                .origFileName(videoName)
                .filePath(location.toString())
                .build();

        videoRepository.save(video);
    }

    /**
     * 비디오 재생
     */
    @Transactional(readOnly = true)
    public ResponseEntity<ResourceRegion> playVideo(HttpHeaders httpHeaders, VideoPlayRequestDto dto) throws IOException {

        Long currentMemberId = SecurityUtil.getLoginMemberId();

        Long uploader = videoRepository.findByOrigFileName(dto.getFile()).get().getMember().getId();

        Set<Authority> authorities = memberRepository.findById(currentMemberId).get().getAuthorities();

        boolean isAdmin = authorities.stream()
                .map(Authority::getAuthorityStatus)
                .anyMatch(a -> a.equals("ROLE_ADMIN"));

        if (!isAdmin && currentMemberId != uploader) {
            throw new RuntimeException("업로더가 아니거나 admin권한이 없습니다.");
        }

        FileUrlResource resource = new FileUrlResource(dir + "\\" +dto.getFile());
        ResourceRegion resourceRegion = resourceRegion(resource, httpHeaders);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resourceRegion);
    }

    /**
     * 비디오 재생 테스트
     */
    public ResponseEntity<ResourceRegion> playVideoTest(HttpHeaders httpHeaders, @PathVariable String name) throws IOException {

        FileUrlResource resource = new FileUrlResource(dir + "\\" + name);
        ResourceRegion resourceRegion = resourceRegion(resource, httpHeaders);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resourceRegion);
    }

    private ResourceRegion resourceRegion(Resource video, HttpHeaders httpHeaders) throws IOException {
        final long chunkSize = 1000000L;
        long contentLength = video.contentLength();

        if (httpHeaders.getRange().isEmpty()) {
            return new ResourceRegion(video, 0, Long.min(chunkSize, contentLength));
        }

        HttpRange httpRange = httpHeaders.getRange().stream().findFirst().get();
        long start = httpRange.getRangeStart(contentLength);
        long end = httpRange.getRangeEnd(contentLength);
        long rangeLength = Long.min(chunkSize, end - start + 1);
        return new ResourceRegion(video, start, rangeLength);
    }

    /**
     * 비디오 전체 조회
     */
    @Transactional(readOnly = true)
    public List<Video> findVideos() {
        return videoRepository.findAll();
    }

}
