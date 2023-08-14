package com.wanted.onboarding.board.service;

import com.wanted.onboarding.board.api.dto.BoardQueryDto.BoardFindAllResponseDto;
import com.wanted.onboarding.board.api.dto.BoardQueryDto.BoardFindByIdResponseDto;
import com.wanted.onboarding.board.api.dto.BoardQueryDto.BoardResponseDto;
import com.wanted.onboarding.board.domain.Board;
import com.wanted.onboarding.board.exception.BoardErrorCode;
import com.wanted.onboarding.board.repository.BoardQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardQueryService implements BoardQueryUsecase {

    private final BoardQueryRepository boardQueryRepository;

    @Override
    public BoardFindAllResponseDto findAll(Pageable pageable) {

        pageable = pageable.previousOrFirst();


        Page<Board> boardPage = boardQueryRepository.findAll(pageable);
        List<BoardResponseDto> boardList = boardPage.stream()
                .map((e)-> BoardResponseDto.builder()
                        .id(e.getId())
                        .title(e.getTitle())
                        .content(e.getContent())
                        .name(e.getName())
                        .memberId(e.getMemberId())
                        .createdAt(e.getCreatedAt())
                        .updatedAt(e.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());

        long lastPageNumber = boardPage.getTotalPages();
        if (pageable.getPageNumber() >= lastPageNumber) {
            throw BoardErrorCode.PAGE_OUT_OF_RANGE.defaultException();
        }

        return BoardFindAllResponseDto.builder()
                .boardList(boardList)
                .lastPage(lastPageNumber)
                .build();
    }

    @Override
    public BoardFindByIdResponseDto findById(Long boardId) {

        Board borad = boardQueryRepository.findById(boardId)
                .orElseThrow(BoardErrorCode.DEFAULT::defaultException);

        return BoardFindByIdResponseDto.builder()
                .title(borad.getTitle())
                .content(borad.getContent())
                .build();
    }
}
