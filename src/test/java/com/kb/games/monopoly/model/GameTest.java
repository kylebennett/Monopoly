package com.kb.games.monopoly.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class GameTest {

  Game game;

  @Mock
  Player player1, player2, player3, player4;

  @BeforeEach
  public void setUp() {
    List<Player> playerList = new ArrayList<>(Arrays.asList(player1, player2, player3, player4));
    game = new Game(playerList);
  }

  @Test
  public void test_nextTurn_increasesCurrentPlayerIndex() {
    assertThat(game.getActivePlayer()).isEqualTo(player1);
    game.nextTurn();
    assertThat(game.getActivePlayer()).isEqualTo(player2);
  }

  @Test
  public void test_nextTurn_currentPlayerIndexLoopsBackTo0() {
    assertThat(game.getActivePlayer()).isEqualTo(player1);
    for (int i = 0; i < 4; i++) {
      game.nextTurn();
    }
    assertThat(game.getActivePlayer()).isEqualTo(player1);
  }

  @Test
  public void test_nextTurn_turnCountIncreasesAfterLastPlayerTurn() {
    assertThat(game.getTurnCount()).isEqualTo(1);
    for (int i = 0; i < 4; i++) {
      game.nextTurn();
    }
    assertThat(game.getTurnCount()).isEqualTo(2);
  }
}
