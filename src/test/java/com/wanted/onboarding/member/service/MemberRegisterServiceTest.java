package com.wanted.onboarding.member.service;

import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberSignUpRequestDto;
import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberSignUpResponseDto;
import com.wanted.onboarding.member.domain.Member;
import com.wanted.onboarding.member.domain.type.MemberStatus;
import com.wanted.onboarding.member.exception.AuthenticationException;
import com.wanted.onboarding.member.repository.MemberRepository;
import com.wanted.onboarding.support.BaseServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MemberRegisterServiceTest extends BaseServiceTest {

    @Autowired
    private MemberRegisterUsecase memberRegisterUsecase;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원가입_성공() {
        MemberSignUpRequestDto memberSignUpRequestDto =
                new MemberSignUpRequestDto("test@test.com", "123456789", "테스트유저");

        MemberSignUpResponseDto memberSignUpResponseDto =
                memberRegisterUsecase.signup(memberSignUpRequestDto);

        Assertions.assertThat(memberSignUpResponseDto.success()).isTrue();
    }

    @Test
    void 회원가입_실패__이메일_중복() {
        memberRepository.save(new Member("test@test.com", "1234562345", "원래 유저", MemberStatus.ACTIVE));

        MemberSignUpRequestDto memberSignUpRequestDto =
                new MemberSignUpRequestDto("test@test.com", "123456789", "테스트유저");

        Assertions.assertThatExceptionOfType(AuthenticationException.class)
                .isThrownBy(() -> memberRegisterUsecase.signup(memberSignUpRequestDto));
    }
}