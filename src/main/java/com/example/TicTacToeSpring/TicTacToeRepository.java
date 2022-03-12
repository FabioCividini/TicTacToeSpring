package com.example.TicTacToeSpring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicTacToeRepository extends JpaRepository<TicTacToeEntity, Long> {
    Optional<TicTacToeEntity> findTopByOrderByIdDesc();
}
