package com.kb.games.monopoly.model;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

  Board board;

  Space space1, space2, space3;

  @BeforeEach
  public void setup() {
    board = new Board();

    space1 = new Space("Space 1");
    space2 = new Space("Space 2");
    space3 = new Space("Space 3");

    board.setSpaces(Arrays.asList(space1, space2, space3));
  }

  @Test
  public void test_getSpace_ReturnsSpaceAtIndex() {
    assertThat(board.getSpace(0)).isEqualTo(space1);
    assertThat(board.getSpace(1)).isEqualTo(space2);
    assertThat(board.getSpace(2)).isEqualTo(space3);
  }
}
