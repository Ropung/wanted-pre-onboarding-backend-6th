package com.wanted.onboarding.member.repository;

import com.wanted.onboarding.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    boolean existsMemberByEmail(String email);
}
