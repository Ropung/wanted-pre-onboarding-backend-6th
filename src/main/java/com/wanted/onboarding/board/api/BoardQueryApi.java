package com.wanted.onboarding.board.api;

import com.wanted.onboarding.board.api.dto.BoardQueryDto.BoardFindAllResponseDto;
import com.wanted.onboarding.board.api.dto.BoardQueryDto.BoardFindByIdResponseDto;
import com.wanted.onboarding.board.service.BoardQueryUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardQueryApi {

    private final BoardQueryUsecase boardQueryUsecase;

    @GetMapping("/list")
    public BoardFindAllResponseDto boardFindAll(
            @PageableDefault(
                    size=5,
                    sort="id",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ){
        return boardQueryUsecase.findAll(pageable);
    }

    @GetMapping("/{id}")
    public BoardFindByIdResponseDto boardFindById(
            @PathVariable Long id
    ){
        return boardQueryUsecase.findById(id);
    }
}