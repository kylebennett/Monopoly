package com.kb.games.monopoly.controllers;

import com.kb.games.monopoly.model.DiceRoll;
import com.kb.games.monopoly.model.Game;
import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

  @Autowired
  private GameService gameService;

  @PostMapping("/start")
  public ResponseEntity<Game> startGame() {
    Game createdGame = gameService.startGame();

    return ResponseEntity.ok(createdGame);
  }

  @GetMapping("/roll")
  public ResponseEntity<DiceRoll> rolllDice() {
    DiceRoll roll = gameService.rollDice();

    return ResponseEntity.ok(roll);
  }

  @GetMapping("/active-player")
  public ResponseEntity<Player> getActivePlayer() {
    Player activePlayer = gameService.getActivePlayer();

    return ResponseEntity.ok(activePlayer);
  }

  @PostMapping("/next-turn")
  public ResponseEntity<Player> nextTurn() {
    Player nextPlayer = gameService.nextTurn();

    return ResponseEntity.ok(nextPlayer);
  }
}
