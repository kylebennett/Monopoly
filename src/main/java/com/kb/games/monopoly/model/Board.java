package com.kb.games.monopoly.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "board")
public class Board {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany
  private Map<Integer, Space> spaces;

  public Board() {
  }

  public void loadSpaces() {
    spaces = new HashMap<>();

    for (int i = 0; i < 40; i++) {
      spaces.put(i, new Space("space " + i));
    }
  }

  public Space getSpace(int location) {
    return spaces.get(location);
  }
}
