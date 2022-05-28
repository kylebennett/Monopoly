package com.kb.games.monopoly.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Table;

@Entity
@Table(name = "game")
public class Game {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany
  private List<Player> playerList;

  private int turnCount = 1;
  private int currentPlayerIndex = 0;

  public Game() {
  }

  public Game(List<Player> players) {
    this.playerList = players;
  }

  /**
   * Progress the game to the next turn. Set the next player as active. If all
   * players have had a turn, first player is active and turn count increases
   */
  public void nextTurn() {
    if (currentPlayerIndex + 1 >= playerList.size()) {
      currentPlayerIndex = 0;
      turnCount += 1;
    } else {
      currentPlayerIndex += 1;
    }
  }

  /**
   * Get the active player
   *
   * @return Player
   */
  public Player getActivePlayer() {
    return playerList.get(currentPlayerIndex);
  }

  /**
   * Get the turn count
   *
   * @return int
   */
  public int getTurnCount() {
    return this.turnCount;
  }
}
