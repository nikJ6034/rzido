package com.coco.rzido.api.repository;

import java.util.Optional;

import com.coco.rzido.api.entity.Api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Api, Long>{
	public Optional<Api> findByUrl(String url);
}
