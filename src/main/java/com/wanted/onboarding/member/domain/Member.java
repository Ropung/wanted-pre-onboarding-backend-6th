package com.wanted.onboarding.member.domain;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.google.common.base.Preconditions;
import com.wanted.onboarding.support.BaseEntity;
import com.wanted.onboarding.member.domain.type.MemberStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    private String email;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public Member(String email, String password, String name, MemberStatus status) {
        Preconditions.checkArgument(Strings.isNotBlank(email));
        Preconditions.checkArgument(!ObjectUtils.isEmpty(status));

        this.email = email;
        this.password = password;
        this.name = name;
        this.status = status;
    }
}
