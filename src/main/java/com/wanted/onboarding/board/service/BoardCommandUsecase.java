package com.wanted.onboarding.board.service;

import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateRequsetDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardCreateResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardRemoveResponseDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardUpdateRequsetDto;
import com.wanted.onboarding.board.api.dto.BoardCommandDto.BoardUpdateResponseDto;
import com.wanted.onboarding.utill.jwt.JwtPayloadParser;

import javax.servlet.http.HttpServletRequest;

public interface BoardCommandUsecase {

    BoardCreateResponseDto create(BoardCreateRequsetDto dto, JwtPayloadParser payloadParser);
    BoardUpdateResponseDto update(BoardUpdateRequsetDto dto, JwtPayloadParser payloadParser);
    BoardRemoveResponseDto remove(Long id, JwtPayloadParser payloadParser);

//    BoardCreateResponseDto create(BoardCreateRequsetDto dto, HttpServletRequest request);
//    BoardUpdateResponseDto update(BoardUpdateRequsetDto dto, HttpServletRequest request);
//    BoardRemoveResponseDto remove(Long id, HttpServletRequest request);
}
