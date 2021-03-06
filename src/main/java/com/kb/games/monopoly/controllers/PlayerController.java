package com.kb.games.monopoly.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.services.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URIConstants.PLAYERS)
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  /**
   * Get all players
   *
   * @return List<Player>
   */
  @GetMapping
  public List<Player> getPlayers() {
    return playerService.getAllPlayers();
  }

  /**
   * Get a specific player
   *
   * @param id
   * @return Player
   */
  @GetMapping("/{id}")
  public Player getPlayer(@PathVariable Long id) {
    return playerService.getPlayer(id).get();
  }

  /**
   * Create a new player
   *
   * @param player
   * @return ResponseEntity<Player>
   * @throws URISyntaxException
   */
  @PostMapping(URIConstants.CREATE)
  public ResponseEntity<Player> createPlayer(@RequestBody Player player) throws URISyntaxException {
    Player savedPlayer = playerService.createPlayer(player);
    return ResponseEntity.created(new URI(URIConstants.PLAYERS + "/" + savedPlayer.getId())).body(savedPlayer);
  }
}
