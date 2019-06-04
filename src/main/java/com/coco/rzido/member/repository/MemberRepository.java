package com.coco.rzido.member.repository;

import java.util.Optional;

import com.coco.rzido.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByMemberName(String memberName);

	@Query(value = "select count(m.id) from Member m where m.memberName = :memberName")
	Long existsByMemberName(@Param("memberName")String memberName);
}
