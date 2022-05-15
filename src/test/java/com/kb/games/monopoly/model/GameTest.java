package com.kb.games.monopoly.model;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

  private Game game;

  @Mock
  private Player player1, player2, player3, player4;

  @BeforeEach
  public void setUp() {

    List<Player> playerList = new ArrayList<>();
    playerList.add(player1);
    playerList.add(player2);
    playerList.add(player3);
    playerList.add(player4);

    game = new Game(playerList);
  }

  @Test
  public void test_nextTurn_increasesCurrentPlayerIndex() {
    assertThat(game.getCurrentPlayer()).isEqualTo(player1);
    game.nextTurn();
    assertThat(game.getCurrentPlayer()).isEqualTo(player2);
  }

  @Test
  public void test_nextTurn_currentPlayerIndexLoopsBackTo0() {
    assertThat(game.getCurrentPlayer()).isEqualTo(player1);
    for (int i = 0; i < 4; i++) {
      game.nextTurn();
    }
    assertThat(game.getCurrentPlayer()).isEqualTo(player1);
  }
}
