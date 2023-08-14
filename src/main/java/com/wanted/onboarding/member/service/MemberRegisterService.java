package com.wanted.onboarding.member.service;

import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberLoginRequestDto;
import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberLoginResponseDto;
import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberSignUpRequestDto;
import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberSignUpResponseDto;
import com.wanted.onboarding.member.domain.Member;
import com.wanted.onboarding.member.domain.type.MemberStatus;
import com.wanted.onboarding.member.exception.AuthenticationErrorCode;
import com.wanted.onboarding.member.exception.AuthenticationException;
import com.wanted.onboarding.member.repository.MemberRepository;
import com.wanted.onboarding.member.service.mapper.MemberDtoMapper;
import com.wanted.onboarding.utill.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRegisterService implements MemberRegisterUsecase {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberDtoMapper mapper;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;


    @Override
    public MemberSignUpResponseDto signup(MemberSignUpRequestDto dto) {

        boolean exists = memberRepository.existsMemberByEmail(dto.email());
        if (exists) throw new AuthenticationException(AuthenticationErrorCode.DEPRECATED);

        String digest = passwordEncoder.encode(dto.rawPassword());
        MemberStatus status = MemberStatus.ACTIVE;

        Member member = mapper.from(
                dto.email(),
                digest,
                dto.name(),
                status
        );
        memberRepository.save(member);

        return MemberSignUpResponseDto.builder()
                .success(true)
                .build();
    }
    @Override
    public MemberLoginResponseDto login(MemberLoginRequestDto dto) {

        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(AuthenticationErrorCode.SIGN_FAIL::defaultException);

        if(!passwordEncoder.matches(dto.rawPassword(),member.getPassword())){
            throw new AuthenticationException(AuthenticationErrorCode.SIGN_FAIL);
        }

        String token = jwtProvider.generateAsUser(dto.email(),member.getName());

        return MemberLoginResponseDto.builder()
                .token(token)
                .build();
    }

}
