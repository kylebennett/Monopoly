package com.kb.games.monopoly.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kb.games.monopoly.model.DiceRoll;
import com.kb.games.monopoly.model.Game;
import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.model.PlayerMove;
import com.kb.games.monopoly.repositories.GameRepository;
import com.kb.games.monopoly.repositories.PlayerRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GameServiceTest {

  @Autowired
  GameService gameService;

  @MockBean
  DiceService diceServiceMock;

  @MockBean
  GameRepository gameRepoMock;

  @MockBean
  PlayerRepository playerRepoMock;

  @Mock
  Game gameMock;

  @Mock
  DiceRoll diceRollMock;

  @Mock
  Player playerMock;

  @Test
  public void test_startGame_createsGame() throws Exception {
    when(gameRepoMock.save(any(Game.class))).thenReturn(gameMock);

    assertThat(gameService.startGame()).isEqualTo(gameMock);
    verify(gameRepoMock).save(any(Game.class));
    verify(playerRepoMock).findAll();
  }

  @Test
  public void test_movePlayer_RollsDiceMovesPlayerSavesPlayer() {
    when(diceServiceMock.rollDice(anyInt())).thenReturn(diceRollMock);
    when(diceRollMock.getTotal()).thenReturn(10);
    when(gameRepoMock.findAll()).thenReturn(Arrays.asList(gameMock));
    when(gameMock.getActivePlayer()).thenReturn(playerMock);
    when(playerMock.getBoardLocation()).thenReturn(2);

    PlayerMove move = gameService.movePlayer();
    assertThat(move.getPlayer()).isEqualTo(playerMock);
    assertThat(move.getRoll()).isEqualTo(diceRollMock);

    verify(playerMock).setBoardLocation(12);
    verify(playerRepoMock).save(playerMock);
  }

  @Test
  public void test_getActiveGame_returnsActiveGame() throws Exception {
    List<Game> gameList = new ArrayList<>(Arrays.asList(gameMock));
    when(gameRepoMock.findAll()).thenReturn(gameList);

    assertThat(gameService.getActiveGame()).isEqualTo(gameMock);
  }

  @Test
  public void test_getActivePlayer_returnsActivePlayer() throws Exception {
    List<Game> gameList = new ArrayList<>(Arrays.asList(gameMock));
    when(gameRepoMock.findAll()).thenReturn(gameList);
    when(gameMock.getActivePlayer()).thenReturn(playerMock);

    assertThat(gameService.getActivePlayer()).isEqualTo(playerMock);
  }

  @Test
  public void test_nextTurn_advancesTurnAndReturnsNextPlayer() throws Exception {
    List<Game> gameList = new ArrayList<>(Arrays.asList(gameMock));
    when(gameRepoMock.findAll()).thenReturn(gameList);
    when(gameMock.getActivePlayer()).thenReturn(playerMock);

    assertThat(gameService.nextTurn()).isEqualTo(playerMock);
    verify(gameMock).nextTurn();
    verify(gameRepoMock).save(eq(gameMock));
  }
}
