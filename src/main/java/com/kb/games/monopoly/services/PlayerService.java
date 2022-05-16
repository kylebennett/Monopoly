package com.kb.games.monopoly.services;

import java.util.List;
import java.util.Optional;

import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.repositories.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  @Autowired
  private PlayerRepository playerRepo;

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
