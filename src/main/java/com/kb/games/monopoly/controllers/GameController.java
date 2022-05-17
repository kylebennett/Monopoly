package com.kb.games.monopoly.controllers;

import com.kb.games.monopoly.model.Game;
import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.model.PlayerMove;
import com.kb.games.monopoly.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URIConstants.GAME)
public class GameController {

  @Autowired
  private GameService gameService;

  /**
   * Start a new Game
   *
   * @return ResponseEntity<Game>
   */
  @PostMapping(URIConstants.START)
  public ResponseEntity<Game> startGame() {
    Game createdGame = gameService.startGame();

    return ResponseEntity.ok(createdGame);
  }

  /**
   * Roll 2 Dice, move the current player to the new location
   *
   * @return ResponseEntity<PlayerMove>
   */
  @PostMapping(URIConstants.MOVE)
  public ResponseEntity<PlayerMove> rollDice() {
    PlayerMove move = gameService.movePlayer();

    return ResponseEntity.ok(move);
  }

  /**
   * Return the active player
   *
   * @return ResponseEntity<Player>
   */
  @GetMapping(URIConstants.ACTIVE_PLAYER)
  public ResponseEntity<Player> getActivePlayer() {
    Player activePlayer = gameService.getActivePlayer();

    return ResponseEntity.ok(activePlayer);
  }

  /**
   * Progress the game to the next turn
   *
   * @return ResponseEntity<Player>
   */
  @PostMapping(URIConstants.NEXT_TURN)
  public ResponseEntity<Player> nextTurn() {
    Player nextPlayer = gameService.nextTurn();

    return ResponseEntity.ok(nextPlayer);
  }
}
