package com.wanted.onboarding.board.service;

import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateRequsetDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardRemoveResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardUpdateRequsetDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardUpdateResponseDto;
import com.wanted.onboarding.board.domain.Board;
import com.wanted.onboarding.board.exxeption.BoardErrorCode;
import com.wanted.onboarding.board.repository.BoardCommandRepository;
import com.wanted.onboarding.member.domain.Member;
import com.wanted.onboarding.member.exception.AuthenticationErrorCode;
import com.wanted.onboarding.member.repository.MemberRepository;
import com.wanted.onboarding.utill.jwt.JwtPayloadParser;
import com.wanted.onboarding.utill.jwt.JwtPayloadParserBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardCommandService implements BoardCommandUsecase {

    private final BoardCommandRepository boardCommandRepository;
    private final MemberRepository memberRepository;
    private final JwtPayloadParserBuilder jwtPayloadParserBuilder;

    @Override
    public BoardCreateResponseDto create(
            BoardCreateRequsetDto dto,
            JwtPayloadParser payloadParser
    ) {

        boolean isTitle = boardCommandRepository.existsBookByTitle(dto.title());
        if (isTitle) throw BoardErrorCode.DEPRECATED.defaultException();
        if (dto.content().isEmpty()) throw BoardErrorCode.DEPRECATED.defaultException();

        // 유저 아이디 유효성 검사
        String email = payloadParser.subject();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(AuthenticationErrorCode.MISMATCHED::defaultException);

        boardCommandRepository.save(
                Board.builder()
                    .title(dto.title())
                    .content(dto.content())
                    .memberId(member.getId())
                    .name(member.getName())
                    .build()
        );

        return BoardCreateResponseDto.builder()
                .success(true)
                .build();
    }

    @Override
    public BoardUpdateResponseDto update(
            BoardUpdateRequsetDto dto,
            JwtPayloadParser payloadParser
    ) {
        return BoardUpdateResponseDto.builder()
                .success(false)
                .build();
    }

    @Override
    public BoardRemoveResponseDto remove(
            Long id,
            JwtPayloadParser payloadParser
    ) {
        return BoardRemoveResponseDto.builder()
                .success(false)
                .build();
    }
}
