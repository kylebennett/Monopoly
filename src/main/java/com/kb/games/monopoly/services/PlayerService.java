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

  /**
   * Return a list of all players
   *
   * @return List<Player>
   */
  public List<Player> getAllPlayers() {
    return playerRepo.findAll();
  }

  /**
   * Return a specific player
   *
   * @param id
   * @return Optional<Player>
   */
  public Optional<Player> getPlayer(Long id) {
    return playerRepo.findById(id);
  }

  /**
   * Add a Player.
   *
   * @param player
   * @return Player
   */
  public Player createPlayer(Player player) {
    return playerRepo.save(player);
  }
}
