package com.wanted.onboarding.board.api.dto;

import com.wanted.onboarding.board.domain.Board;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.List;

public record BoardQueryDto() {



    @Builder
    public record BoardFindByIdResponseDto(
            String title,
            String content
    ){}

    @Builder
    public record BoardFindAllResponseDto(
            List<BoardResponseDto> boardList,
            Long lastPage
    ){}

    @Builder
    public record BoardResponseDto(
            Long id,
            String title,
            String content,
            String name,
            Instant createdAt,
            Instant updatedAt
    ){}




}
