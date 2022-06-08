package me.bi.videostorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDto {

    private String memberName;
    private String phone;
    private String password;
    private List<String> authorities;
}