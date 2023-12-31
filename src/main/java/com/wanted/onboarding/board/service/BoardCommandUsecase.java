package com.wanted.onboarding.board.service;

import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateRequsetDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardRemoveResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardUpdateRequsetDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardUpdateResponseDto;

public interface BoardCommandUsecase {

    BoardCreateResponseDto create(Long memberId, BoardCreateRequsetDto dto);
    BoardUpdateResponseDto update(Long memberId, Long boardId, BoardUpdateRequsetDto dto);
    BoardRemoveResponseDto remove(Long memberId, Long boardId);

//    BoardCreateResponseDto create(BoardCreateRequsetDto dto, HttpServletRequest request);
//    BoardUpdateResponseDto update(BoardUpdateRequsetDto dto, HttpServletRequest request);
//    BoardRemoveResponseDto remove(Long id, HttpServletRequest request);
}
