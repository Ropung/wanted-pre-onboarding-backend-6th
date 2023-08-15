package com.wanted.onboarding.member.domain;

import com.wanted.onboarding.member.domain.type.MemberStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    public void 멤버_생성_성공() {
        Member member = new Member("test@email.com", "1234", "테스트유저", MemberStatus.ACTIVE);

        Assertions.assertThat(member.getEmail()).isEqualTo("test@email.com");
        Assertions.assertThat(member.getPassword()).isEqualTo("1234");
        Assertions.assertThat(member.getName()).isEqualTo("테스트유저");
        Assertions.assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void 멤버_생성_실패__이메일이_null_혹은_빈값(String email) {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Member(email, "1234", "테스트유저", MemberStatus.ACTIVE));
    }


    @ParameterizedTest
    @NullSource
    public void 멤버_생성_실패__status가_null(MemberStatus status) {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Member("test@email.com", "1234", "테스트유저", status));
    }

}