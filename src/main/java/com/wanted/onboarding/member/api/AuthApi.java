package com.wanted.onboarding.member.api;

import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberLoginRequestDto;
import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberLoginResponseDto;
import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberSignUpRequestDto;
import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberSignUpResponseDto;
import com.wanted.onboarding.member.service.MemberRegisterService;
import com.wanted.onboarding.member.service.MemberRegisterUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AuthApi {

    private final MemberRegisterUsecase memberRegisterUsecase;

    @PostMapping("/signup")
    public MemberSignUpResponseDto signUp(
            @RequestBody @Valid MemberSignUpRequestDto dto
    ){
        return memberRegisterUsecase.signup(dto);
    }

    @PostMapping("/login")
    public MemberLoginResponseDto login(
            @RequestBody @Valid MemberLoginRequestDto dto
    ){
        return memberRegisterUsecase.login(dto);
    }
}
