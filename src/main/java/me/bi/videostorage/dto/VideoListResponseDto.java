package me.bi.videostorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoListResponseDto<T> {
    private int count;
    private T data;
}