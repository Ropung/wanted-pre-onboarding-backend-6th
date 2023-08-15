package com.wanted.onboarding.member.repository;

import com.wanted.onboarding.member.domain.Member;
import com.wanted.onboarding.member.domain.type.MemberStatus;
import com.wanted.onboarding.support.BaseRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest extends BaseRepositoryTest {
    // Repository 역할은 영속성 객체를 만드느냐

    // 영속성 객체 = 1차 캐싱 되어있냐 -> 저장 -> Persistent Context (영속성 컨텍스트)
    // 우리가 뭔가 인서트 -> (영속성컨텍스트에 저장)->바로 DB접근 -> 저장
    // 우리가 있는거(DB에 있음)를 수정함 -> update쿼리 날리냐? --> 3개 1개 , 1개 1개 -> 한번에 3개를 한꺼번에
    // @Transactional --> PSA => Portable Service Abstraction --> 어떤 Bean? -- PlatformTransactionManager
    // Spring 3대 요소
    // IOC, PSA, AOP

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