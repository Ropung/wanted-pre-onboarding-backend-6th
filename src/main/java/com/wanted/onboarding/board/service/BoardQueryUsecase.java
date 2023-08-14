package com.wanted.onboarding.board.service;

import com.wanted.onboarding.board.api.dto.BoardQueryDto.BoardFindAllResponseDto;
import com.wanted.onboarding.board.api.dto.BoardQueryDto.BoardFindByIdResponseDto;
import org.springframework.data.domain.Pageable;

public interface BoardQueryUsecase {

    BoardFindAllResponseDto findAll(Pageable pageable);

    BoardFindByIdResponseDto findById(Long boardId);
}
