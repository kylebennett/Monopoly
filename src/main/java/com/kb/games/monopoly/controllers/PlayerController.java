package com.kb.games.monopoly.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.repositories.PlayerRepository;

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

  private final PlayerRepository playerRepo;

  public PlayerController(PlayerRepository playerRepo) {
    this.playerRepo = playerRepo;
  }

  @GetMapping
  public List<Player> getPlayers() {
    return playerRepo.findAll();
  }

  @GetMapping("/{id}")
  public Player getPlayer(@PathVariable Long id) {
    return playerRepo.findById(id).orElseThrow(RuntimeException::new);
  }

  @PostMapping("/create")
  public ResponseEntity<Player> createPlayer(@RequestBody Player player) throws URISyntaxException {
    Player savedPlayer = playerRepo.save(player);
    return ResponseEntity.created(new URI("/players/" + savedPlayer.getId())).body(savedPlayer);
  }
}
