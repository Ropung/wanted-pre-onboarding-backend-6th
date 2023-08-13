package com.wanted.onboarding.board.service;

import com.wanted.onboarding.board.repository.BoardCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommandService implements BoardCommandUsecase {

    private final BoardCommandRepository boardCommandRepository;




}
