package com.kb.games.monopoly.repositories;

import com.kb.games.monopoly.model.Game;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
