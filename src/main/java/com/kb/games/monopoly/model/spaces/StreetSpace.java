package com.kb.games.monopoly.model.spaces;

public class StreetSpace extends BuyableSpace {

  private StreetColour colour;
  private int houseCount = 0;
  private boolean hasHotel = false;

  public StreetSpace(String name, int value, StreetColour colour) {
    super(name, value);
    this.colour = colour;
  }

  public StreetColour getColour() {
    return this.colour;
  }

  public void setColour(StreetColour colour) {
    this.colour = colour;
  }

  public int getHouseCount() {
    return this.houseCount;
  }

  public void setHouseCount(int houseCount) {
    this.houseCount = houseCount;
  }

  public boolean isHasHotel() {
    return this.hasHotel;
  }

  public boolean getHasHotel() {
    return this.hasHotel;
  }

  public void setHasHotel(boolean hasHotel) {
    this.hasHotel = hasHotel;
  }
}
