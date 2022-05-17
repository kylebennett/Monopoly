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
  private int boardLocation = 0;

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

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

  /**
   * Get the index of the space the player is currently on.
   * 
   * @return int
   */
  public int getBoardLocation() {
    return this.boardLocation;
  }

  /**
   * Set the index of the space the player is on.
   *
   * @param boardLocation
   */
  public void setBoardLocation(int boardLocation) {
    this.boardLocation = boardLocation;
  }
}
