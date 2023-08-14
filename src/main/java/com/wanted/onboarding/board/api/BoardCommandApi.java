package com.wanted.onboarding.board.api;

import com.wanted.onboarding.board.api.dto.BoardCommandDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateRequsetDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardRemoveResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardUpdateResponseDto;
import com.wanted.onboarding.board.service.BoardCommandUsecase;
import com.wanted.onboarding.utill.jwt.JwtPayloadParserBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardCommandApi {

    private final BoardCommandUsecase boardCommandUsecase;
    private final JwtPayloadParserBuilder jwtPayloadParserBuilder;

    @PostMapping("")
    public BoardCreateResponseDto create(
            @RequestBody @Valid BoardCreateRequsetDto dto,
            HttpServletRequest request
    ){
        return boardCommandUsecase.create(
                dto,
                jwtPayloadParserBuilder.buildWith(request)
        );
    }

    @PutMapping("/{id}")
    public BoardUpdateResponseDto update(
            @PathVariable Long id,
            @RequestBody @Valid BoardCommandDto.BoardUpdateRequsetDto dto,
            HttpServletRequest request) {

        return boardCommandUsecase.update(
                dto,
                jwtPayloadParserBuilder.buildWith(request)
        );
    }

    @DeleteMapping("/{id}")
    public BoardRemoveResponseDto delete(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        return boardCommandUsecase.remove(
                id,
                jwtPayloadParserBuilder.buildWith(request)
        );
    }


}
