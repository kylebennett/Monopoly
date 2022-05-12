package com.kb.games.monopoly.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class Game {

  @Id
  @GeneratedValue
  private Long id;
}
