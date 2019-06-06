package com.coco.rzido.api.repository;

import com.coco.rzido.api.entity.ApiRole;
import com.coco.rzido.api.entity.ApiRoleKey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRoleRepository extends JpaRepository<ApiRole, ApiRoleKey>{

}
