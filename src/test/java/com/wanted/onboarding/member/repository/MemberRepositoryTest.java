package com.wanted.onboarding.member.repository;

import com.wanted.onboarding.member.domain.Member;
import com.wanted.onboarding.member.domain.type.MemberStatus;
import com.wanted.onboarding.support.BaseRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest extends BaseRepositoryTest {


    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 이메일_중복_검사__이메일이_중복() {
        String email = "test@test.com";

        memberRepository.save(new Member(email, "123", "테스트유저", MemberStatus.ACTIVE));

        boolean isExists = memberRepository.existsMemberByEmail(email);

        Assertions.assertThat(isExists).isTrue();
    }

    @Test
    void 이메일_중복_검사__이메일이_중복_아님() {
        String email = "test@test.com";

        boolean isExists = memberRepository.existsMemberByEmail(email);

        Assertions.assertThat(isExists).isFalse();
    }
}