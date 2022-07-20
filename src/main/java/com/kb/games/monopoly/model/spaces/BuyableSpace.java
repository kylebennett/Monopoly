package com.kb.games.monopoly.model.spaces;

public abstract class BuyableSpace extends Space {

  private int value;

  public BuyableSpace(String name, int value) {
    super(name);
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }

  public void setValue(int value) {
    this.value = value;
  }

}
