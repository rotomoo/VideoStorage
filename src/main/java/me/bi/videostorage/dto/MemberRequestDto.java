package me.bi.videostorage.dto;

import lombok.*;
import me.bi.videostorage.domain.Authority;
import me.bi.videostorage.domain.Member;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String email;
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder, Set<Authority> authorities) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .authorities(authorities)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}