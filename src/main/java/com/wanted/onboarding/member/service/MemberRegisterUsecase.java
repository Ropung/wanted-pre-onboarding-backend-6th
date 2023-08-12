package com.wanted.onboarding.member.service;

import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberLoginRequestDto;
import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberLoginResponseDto;
import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberSignUpRequestDto;
import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberSignUpResponseDto;

public interface MemberRegisterUsecase {

    MemberSignUpResponseDto signup(MemberSignUpRequestDto dto);
    MemberLoginResponseDto login(MemberLoginRequestDto dto);
}
