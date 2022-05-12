package com.kb.games.monopoly.controllers;

import com.kb.games.monopoly.model.Game;
import com.kb.games.monopoly.repositories.GameRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

  private final GameRepository gameRepo;

  public GameController(GameRepository gameRepo) {
    this.gameRepo = gameRepo;
  }

  @PostMapping("/start")
  public ResponseEntity<Game> startGame(@RequestBody Game game) {
    Game createdGame = gameRepo.save(game);

    return ResponseEntity.ok(createdGame);
  }
}
