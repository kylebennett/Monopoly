package com.kb.games.monopoly.services;

import java.util.List;
import java.util.Optional;

import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.repositories.PlayerRepository;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private PlayerRepository playerRepo;

  public PlayerService(PlayerRepository playerRepo) {
    this.playerRepo = playerRepo;
  }

  public List<Player> getAllPlayers() {
    return playerRepo.findAll();
  }

  public Optional<Player> getPlayer(Long id) {
    return playerRepo.findById(id);
  }

  public Player createPlayer(Player player) {
    return playerRepo.save(player);
  }
}
