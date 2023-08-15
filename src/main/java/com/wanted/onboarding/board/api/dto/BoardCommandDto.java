package com.wanted.onboarding.board.api.dto;

import lombok.Builder;

import javax.validation.constraints.NotNull;

public record BoardCommandDto() {


    public record BoardCreateRequsetDto(
            String title,
            String content
    ){}
    @Builder
    public record BoardCreateResponseDto(
            boolean success
    ){}

    public record BoardUpdateRequsetDto(
            String title,
            String content
    ){}
    @Builder
    public record BoardUpdateResponseDto(
            boolean success
    ){}

    @Builder
    public record BoardRemoveResponseDto(
            boolean success
    ){}

}
