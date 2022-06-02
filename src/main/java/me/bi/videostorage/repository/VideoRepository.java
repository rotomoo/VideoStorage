package me.bi.videostorage.repository;

import me.bi.videostorage.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
