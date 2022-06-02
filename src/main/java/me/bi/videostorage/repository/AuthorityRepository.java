package me.bi.videostorage.repository;

import me.bi.videostorage.domain.Authority;
import me.bi.videostorage.domain.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityEnum> {
    Optional<Authority> findByAuthorityStatus(AuthorityEnum authorityStatus);
}