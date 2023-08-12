package com.wanted.onboarding.member.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record MemberRegisterDto() {

    public record MemberSignUpRequestDto(
            @Email
            @NotBlank
            String email,
            @JsonProperty("password")
            @Pattern(
                regexp = "^(.{8,})$",
                message ="비밀번호 양식을 확인해주세요."
            )
            String rawPassword,
            String name
    ){}
    @Builder
    public record MemberSignUpResponseDto(
            boolean success
    ){}
    public record MemberLoginRequestDto(
            @Email
            String email,
            @JsonProperty("password")
            @Pattern(
                    regexp = "^(.{8,})$",
                    message ="비밀번호 양식을 확인해주세요."
            )
            String rawPassword
    ){}
    @Builder
    public record MemberLoginResponseDto(
            @JsonInclude(Include.NON_EMPTY)
            String token
    ){}
}
