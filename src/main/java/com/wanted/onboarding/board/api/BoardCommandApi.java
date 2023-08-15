package com.wanted.onboarding.board.api;

import com.wanted.onboarding.board.api.dto.BoardCommandDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardUpdateRequsetDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateRequsetDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardRemoveResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardUpdateResponseDto;
import com.wanted.onboarding.board.service.BoardCommandUsecase;
import com.wanted.onboarding.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

//    @PreAuthorize("hasRole(CREATE)")
    @PostMapping
    public BoardCreateResponseDto create(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody @Valid BoardCreateRequsetDto dto
    ){
        return boardCommandUsecase.create(customUserDetails.getId(), dto);
    }

    @PutMapping("/{boardId}")
    public BoardUpdateResponseDto update(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long boardId,
            @RequestBody @Valid BoardUpdateRequsetDto dto
    ) {
        return boardCommandUsecase.update(
                customUserDetails.getId(),
                boardId,
                dto);
    }

    @DeleteMapping("/{boardId}")
    public BoardRemoveResponseDto delete(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long boardId
    ) {
        return boardCommandUsecase.remove(customUserDetails.getId(),boardId);
    }


}
