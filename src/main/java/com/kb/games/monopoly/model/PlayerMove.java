package com.kb.games.monopoly.model;

public class PlayerMove {

  private final Player player;
  private final DiceRoll roll;

  public PlayerMove(Player player, DiceRoll roll) {
    this.player = player;
    this.roll = roll;
  }

  /**
   * Get the player the move applies to
   *
   * @return Player
   */
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Get the dice rol for the move
   *
   * @return DiceRoll
   */
  public DiceRoll getRoll() {
    return this.roll;
  }
}
