package com.kb.games.monopoly.model;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "player")
public class Player {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  public Player() {
    super();
  }

  public Player(String name) {
    super();
    this.name = name;
  }

  /**
   * Get the ID
   *
   * @return Long
   */
  public Long getId() {
    return this.id;
  }

  /**
   * Get the player's name
   *
   * @return String
   */
  public String getName() {
    return this.name;
  }
}
