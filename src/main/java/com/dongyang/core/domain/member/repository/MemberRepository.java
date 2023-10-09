package com.dongyang.core.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongyang.core.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
}
