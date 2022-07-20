package com.kb.games.monopoly.model.spaces;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spaces")
public abstract class Space {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  public Space() {
  }

  public Space(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
