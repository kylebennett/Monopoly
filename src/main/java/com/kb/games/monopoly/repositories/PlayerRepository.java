package com.kb.games.monopoly.repositories;

import com.kb.games.monopoly.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}