package com.wanted.onboarding.member.api;

import com.wanted.onboarding.member.api.dto.MemberRegisterDto.MemberSignUpRequestDto;
import com.wanted.onboarding.member.domain.Member;
import com.wanted.onboarding.member.domain.type.MemberStatus;
import com.wanted.onboarding.member.repository.MemberRepository;
import com.wanted.onboarding.support.BaseApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

class AuthApiTest extends BaseApiTest {

    @Autowired
    private MemberRepository memberRepository;


    @Test
    void 회원가입_성공() throws Exception {
        MemberSignUpRequestDto request =
                new MemberSignUpRequestDto("test@test.com", "123456789", "테스트유저");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))

        )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.success").value(true)
                );
    }

    @Test
    void 회원가입_실패__이메일_중복() throws Exception {

        memberRepository.save(new Member("test@test.com", "testPassword1234", "먼저 가입한 유저", MemberStatus.ACTIVE));

        MemberSignUpRequestDto request =
                new MemberSignUpRequestDto("test@test.com", "123456789", "테스트유저");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))

        ).andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.jsonPath("$").value("중복된 이메일 입니다.")
                );

    }
}