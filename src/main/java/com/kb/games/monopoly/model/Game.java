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

  private int currentPlayerIndex = 0;

  public Game() {
  }

  public Game(List<Player> players) {
    this.playerList = players;
  }

  public void nextTurn() {
    if (currentPlayerIndex + 1 >= playerList.size()) {
      currentPlayerIndex = 0;
    } else {
      currentPlayerIndex += 1;
    }
  }

  public Player getCurrentPlayer() {
    return playerList.get(currentPlayerIndex);
  }
}
