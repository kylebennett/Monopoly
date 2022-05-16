package com.kb.games.monopoly.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DiceRollTest {

  @ParameterizedTest
  @ValueSource(ints = { 1, 3, 5, 100 })
  public void test_RollsRequiredNumberOfDice(int count) {
    DiceRoll roll = new DiceRoll(count);
    assertThat(roll.getResults()).hasSize(count);
  }

  @Test
  public void test_RollsValuesBetween1and6() {
    DiceRoll roll = new DiceRoll(20);

    for (int result : roll.getResults()) {
      assertThat(result).isBetween(1, 6);
    }
  }
}
