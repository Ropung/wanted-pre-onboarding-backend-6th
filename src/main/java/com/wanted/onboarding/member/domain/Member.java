package com.wanted.onboarding.member.domain;
import com.wanted.onboarding.core.BaseEntity;
import com.wanted.onboarding.member.domain.type.MemberStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private MemberStatus status= MemberStatus.ACTIVE;

}
