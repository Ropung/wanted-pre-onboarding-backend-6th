package com.wanted.onboarding.board.repository;

import com.wanted.onboarding.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommandRepository extends JpaRepository<Board,Long> {

    boolean existsBoardByTitle(String title);
}
