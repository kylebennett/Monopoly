package com.kb.games.monopoly.controllers;

import java.util.List;

import com.kb.games.monopoly.model.DiceRoll;
import com.kb.games.monopoly.model.Game;
import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.repositories.GameRepository;
import com.kb.games.monopoly.repositories.PlayerRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

  private final GameRepository gameRepo;
  private final PlayerRepository playerRepo;

  public GameController(GameRepository gameRepo, PlayerRepository playerRepo) {
    this.gameRepo = gameRepo;
    this.playerRepo = playerRepo;
  }

  @PostMapping("/start")
  public ResponseEntity<Game> startGame() {

    List<Player> players = playerRepo.findAll();

    Game createdGame = new Game(players);
    gameRepo.save(createdGame);

    return ResponseEntity.ok(createdGame);
  }

  @GetMapping("/roll")
  public ResponseEntity<DiceRoll> rolllDice() {
    DiceRoll roll = new DiceRoll(2);

    return ResponseEntity.ok(roll);
  }

  @GetMapping("/current-player")
  public ResponseEntity<Player> getCurrentPlayer() {
    Player currentPlayer = getActiveGame().getCurrentPlayer();

    return ResponseEntity.ok(currentPlayer);
  }

  @PostMapping("/next-turn")
  public ResponseEntity<Player> nextTurn() {
    Game game = getActiveGame();
    game.nextTurn();
    gameRepo.save(game);

    return ResponseEntity.ok(game.getCurrentPlayer());
  }

  private Game getActiveGame() {
    return gameRepo.findAll().get(0);
  }
}
