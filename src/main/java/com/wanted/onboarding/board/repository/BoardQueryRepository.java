package com.wanted.onboarding.board.repository;

import com.wanted.onboarding.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardQueryRepository extends JpaRepository<Board,Long> {

    Page<Board> findAll(Pageable pageable);
    Optional<Board> findById(String id);
}
