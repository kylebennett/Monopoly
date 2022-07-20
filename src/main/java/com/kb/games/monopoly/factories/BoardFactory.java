package com.kb.games.monopoly.factories;

import com.kb.games.monopoly.model.Board;

import org.springframework.stereotype.Service;

@Service
public class BoardFactory {

  public Board loadDefaultBoard() {

    Board board = new Board();

    return board;
  }
}
