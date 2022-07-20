package com.kb.games.monopoly.model.spaces;

public class UtilitySpace extends BuyableSpace {

  private static final int UTILITY_VALUE = 150;

  public UtilitySpace(String name) {
    super(name, UTILITY_VALUE);
  }

}