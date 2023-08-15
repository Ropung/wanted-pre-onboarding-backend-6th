package com.wanted.onboarding.board.service;

import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateRequsetDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardRemoveResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardUpdateRequsetDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardUpdateResponseDto;
import com.wanted.onboarding.board.domain.Board;
import com.wanted.onboarding.board.exception.BoardErrorCode;
import com.wanted.onboarding.board.repository.BoardCommandRepository;
import com.wanted.onboarding.member.domain.Member;
import com.wanted.onboarding.member.exception.AuthenticationErrorCode;
import com.wanted.onboarding.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class BoardCommandService implements BoardCommandUsecase {

    private final BoardCommandRepository boardCommandRepository;
    private final MemberRepository memberRepository;

    @Override
    public BoardCreateResponseDto create(Long id, BoardCreateRequsetDto dto) {

        boolean isTitle = boardCommandRepository.existsBoardByTitle(dto.title());
        if (isTitle) throw BoardErrorCode.DEPRECATED.defaultException();
        if (dto.title().isEmpty()) throw BoardErrorCode.DEPRECATED.defaultException();
        if (dto.content().isEmpty()) throw BoardErrorCode.DEPRECATED.defaultException();

        Member member = memberRepository.findById(id)
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
    @Transactional
    public BoardUpdateResponseDto update(
            Long memberId,
            Long boardId,
            BoardUpdateRequsetDto dto) {

        boolean isTitle = boardCommandRepository.existsBoardByTitle(dto.title());
        if (isTitle) throw BoardErrorCode.DEPRECATED.defaultException();
        if (dto.title().isEmpty()) throw BoardErrorCode.DEPRECATED.defaultException();
        if (dto.content().isEmpty()) throw BoardErrorCode.DEPRECATED.defaultException();

        Board boardEntity = boardCommandRepository.findById(boardId)
                .orElseThrow(BoardErrorCode.DEFAULT::defaultException);

        if(boardEntity.getMemberId() != memberId)
            throw BoardErrorCode.FORBIDDEN.defaultException();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(AuthenticationErrorCode.UNAUTHORIZED::defaultException);

        boardEntity.changeTitle(dto.title());
        boardEntity.changeContent(dto.content());

        return BoardUpdateResponseDto.builder()
                .success(true)
                .build();
    }

    @Override
//    @Transactional
    public BoardRemoveResponseDto remove(
            Long memberId,
            Long boardId
    ) {
        Board board = boardCommandRepository.findById(boardId)
                .orElseThrow(BoardErrorCode.DEFAULT::defaultException);

        if(board.getMemberId() != memberId)
            throw BoardErrorCode.FORBIDDEN.defaultException();

        boardCommandRepository.delete(board);

        return BoardRemoveResponseDto.builder()
                .success(true)
                .build();
    }
}
