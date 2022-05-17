package com.kb.games.monopoly.controllers;

import static org.mockito.Mockito.when;

import com.kb.games.monopoly.model.DiceRoll;
import com.kb.games.monopoly.model.Game;
import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.model.PlayerMove;
import com.kb.games.monopoly.services.GameService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(GameController.class)
public class GameControllerTest {

  @MockBean
  GameService gameServiceMock;

  @Autowired
  MockMvc mvc;

  Game game;
  Player player1, player2;

  final String player1Name = "Bob";
  final String player2Name = "Sally";

  @BeforeEach
  public void setUp() {
    player1 = new Player(player1Name);
    player2 = new Player(player2Name);
    List<Player> playerList = new ArrayList<>(Arrays.asList(player1, player2));

    game = new Game(playerList);
  }

  @Test
  public void test_startGame_returnsGame() throws Exception {
    when(gameServiceMock.startGame()).thenReturn(game);
    mvc.perform(post(URIConstants.GAME + URIConstants.START))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.turnCount").value(1))
        .andExpect(jsonPath("$.activePlayer.name").value(player1Name))
        .andDo(print());
  }

  @Test
  public void test_move_returnsPlayerMove() throws Exception {
    PlayerMove playerMove = new PlayerMove(player1, new DiceRoll(2));
    when(gameServiceMock.movePlayer()).thenReturn(playerMove);

    mvc.perform(post(URIConstants.GAME + URIConstants.MOVE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.player.name").value(equalTo(player1.getName())))
        .andExpect(jsonPath("$.roll.results").value(hasSize(2)))
        .andExpect(jsonPath("$.roll.results.[0]").value(greaterThanOrEqualTo(1)))
        .andExpect(jsonPath("$.roll.results.[0]").value(lessThanOrEqualTo(6)))
        .andExpect(jsonPath("$.roll.results.[1]").value(greaterThanOrEqualTo(1)))
        .andExpect(jsonPath("$.roll.results.[1]").value(lessThanOrEqualTo(6)))
        .andExpect(jsonPath("$.roll.total").exists())
        .andDo(print());
  }

  @Test
  public void test_getActivePlayer_returnsPlayer() throws Exception {
    when(gameServiceMock.getActivePlayer()).thenReturn(player1);

    mvc.perform(get(URIConstants.GAME + URIConstants.ACTIVE_PLAYER))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(player1.getName()))
        .andDo(print());
  }

  @Test
  public void test_nextTurn_returnsNextPlayer() throws Exception {
    when(gameServiceMock.nextTurn()).thenReturn(player1);

    mvc.perform(post(URIConstants.GAME + URIConstants.NEXT_TURN))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(player1.getName()))
        .andDo(print());
  }
}
