package com.wanted.onboarding.member.service.mapper;

import com.wanted.onboarding.member.domain.type.MemberStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.wanted.onboarding.member.domain.Member;
import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberSignUpRequestDto;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface MemberDtoMapper {
//    @Mapping(target = "password", source = "rawPassword")
    Member from(String email, String password, String name, MemberStatus status);
}
