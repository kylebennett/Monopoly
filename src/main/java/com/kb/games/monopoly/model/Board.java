package com.kb.games.monopoly.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kb.games.monopoly.model.spaces.Space;

@Entity
@Table(name = "board")
public class Board {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany
  private List<Space> spaces;

  public Board() {
  }

  public Space getSpace(int location) {
    return spaces.get(location);
  }

  public Long getId() {
    return this.id;
  }

  public List<Space> getSpaces() {
    return this.spaces;
  }

  public void setSpaces(List<Space> spaces) {
    this.spaces = spaces;
  }
}
