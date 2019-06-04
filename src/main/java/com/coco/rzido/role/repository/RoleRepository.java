package com.coco.rzido.role.repository;

import java.util.Optional;

import com.coco.rzido.role.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Optional<Role> findByRoleName(String RoleName);
}
