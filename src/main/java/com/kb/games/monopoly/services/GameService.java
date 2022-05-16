package com.kb.games.monopoly.services;

import java.util.List;

import com.kb.games.monopoly.model.DiceRoll;
import com.kb.games.monopoly.model.Game;
import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.repositories.GameRepository;
import com.kb.games.monopoly.repositories.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  @Autowired
  private GameRepository gameRepo;

  @Autowired
  private PlayerRepository playerRepo;

  public Game startGame() {
    List<Player> players = playerRepo.findAll();

    Game createdGame = new Game(players);
    return gameRepo.save(createdGame);
  }

  public DiceRoll rollDice() {
    DiceRoll roll = new DiceRoll(2);
    return roll;
  }

  public Player getActivePlayer() {
    Player activePlayer = getActiveGame().getActivePlayer();
    return activePlayer;
  }

  public Game getActiveGame() {
    return gameRepo.findAll().get(0);
  }

  public Player nextTurn() {
    Game game = getActiveGame();
    game.nextTurn();
    gameRepo.save(game);

    return game.getActivePlayer();
  }
}
