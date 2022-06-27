package me.bi.videostorage.repository;

import me.bi.videostorage.domain.RefreshToken;
import me.bi.videostorage.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByOrigFileName(String origFileName);
}
