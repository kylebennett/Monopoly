package com.kb.games.monopoly.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceRoll {

  private final List<Integer> results = new ArrayList<Integer>();
  private final Random random = new Random();

  public DiceRoll(int dice) {
    for (int i = 0; i < dice; i++) {
      results.add(random.nextInt(6) + 1);
    }
  }

  public List<Integer> getResults() {
    return this.results;
  }
}
