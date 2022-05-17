package com.kb.games.monopoly.services;

import java.util.List;

import com.kb.games.monopoly.model.DiceRoll;
import com.kb.games.monopoly.model.Game;
import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.model.PlayerMove;
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

  @Autowired
  private DiceService diceService;

  /**
   * Start a game. Get all current players in the repo and add them to the game.
   *
   * @return Game
   */
  public Game startGame() {
    List<Player> players = playerRepo.findAll();

    Game createdGame = new Game(players);
    return gameRepo.save(createdGame);
  }

  /**
   * Roll 2 dice, move the active player to the new position and save.
   *
   * @return DiceRoll
   */
  public PlayerMove movePlayer() {
    DiceRoll roll = diceService.rollDice(2);
    Player player = getActivePlayer();
    int newPosition = player.getBoardLocation() + roll.getTotal();

    player.setBoardLocation(newPosition);
    playerRepo.save(player);

    PlayerMove move = new PlayerMove(player, roll);

    return move;
  }

  /**
   * Return the active player.
   *
   * @return Player
   */
  public Player getActivePlayer() {
    Player activePlayer = getActiveGame().getActivePlayer();
    return activePlayer;
  }

  /**
   * Return the active Game
   *
   * @return Game
   */
  public Game getActiveGame() {
    return gameRepo.findAll().get(0);
  }

  /**
   * Get the active game and progress to the next turn. Return the new active
   * player.
   *
   * @return Player
   */
  public Player nextTurn() {
    Game game = getActiveGame();
    game.nextTurn();
    gameRepo.save(game);

    return game.getActivePlayer();
  }
}
