package com.coco.rzido.social.repository;

import java.util.Optional;

import com.coco.rzido.social.entity.Social;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<Social,Long> {
    Optional<Social> findByKindAndSocialKey(String kind, String socialKey);
}
