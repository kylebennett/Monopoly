package com.kb.games.monopoly.services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.kb.games.monopoly.model.DiceRoll;

public class DiceServiceTest {

  DiceService diceService = new DiceService();

  @Test
  public void test_rollDice_returnsDiceRoll() throws Exception {
    assertThat(diceService.rollDice(2)).isInstanceOf(DiceRoll.class);
  }

  @Test
  void test_RollDice_returnsSpecifiedNumberOfDice() throws Exception {
    DiceRoll roll = diceService.rollDice(3);
    assertThat(roll.getResults()).hasSize(3);
  }

}
