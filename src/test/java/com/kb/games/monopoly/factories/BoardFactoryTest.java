package com.kb.games.monopoly.factories;

import org.junit.jupiter.api.Test;

import com.kb.games.monopoly.model.Board;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {

  @Test
  public void test_loadDefaultBoard_ReturnsDefaultBoard() throws Exception {
    BoardFactory boardFactory = new BoardFactory();

    Board board = boardFactory.loadDefaultBoard();

    assertThat(board.getSpaces().size()).isEqualTo(2);
  }
}
