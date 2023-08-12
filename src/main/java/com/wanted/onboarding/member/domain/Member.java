package com.wanted.onboarding.member.domain;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.wanted.onboarding.support.BaseEntity;
import com.wanted.onboarding.member.domain.type.MemberStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Member extends BaseEntity {

    private String email;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

}
