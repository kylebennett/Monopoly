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
@RequestMapping("/players")
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  @GetMapping
  public List<Player> getPlayers() {
    return playerService.getAllPlayers();
  }

  @GetMapping("/{id}")
  public Player getPlayer(@PathVariable Long id) {
    return playerService.getPlayer(id).get();
  }

  @PostMapping("/create")
  public ResponseEntity<Player> createPlayer(@RequestBody Player player) throws URISyntaxException {
    Player savedPlayer = playerService.createPlayer(player);
    return ResponseEntity.created(new URI("/players/" + savedPlayer.getId())).body(savedPlayer);
  }
}
