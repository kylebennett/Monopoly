package com.kb.games.monopoly.services;

import com.kb.games.monopoly.model.DiceRoll;

import org.springframework.stereotype.Service;

@Service
public class DiceService {

  /**
   * Roll a specified number of dice
   *
   * @param dice
   * @return DiceRoll
   */
  public DiceRoll rollDice(int dice) {
    return new DiceRoll(dice);
  }
}
