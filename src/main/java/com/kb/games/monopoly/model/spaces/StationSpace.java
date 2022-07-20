package com.kb.games.monopoly.model.spaces;

public class StationSpace extends BuyableSpace {

  private static final int STATION_VALUE = 200;

  public StationSpace(String name) {
    super(name, STATION_VALUE);
  }

}
