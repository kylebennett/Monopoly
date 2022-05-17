package com.kb.games.monopoly.controllers;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.games.monopoly.model.DiceRoll;
import com.kb.games.monopoly.model.Game;
import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.model.PlayerMove;
import com.kb.games.monopoly.services.GameService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(GameController.class)
public class GameControllerTest {

  @MockBean
  GameService gameServiceMock;

  @Autowired
  MockMvc mvc;

  @Autowired
  ObjectMapper objectMapper;

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
    MvcResult mvcResult = mvc.perform(post(URIConstants.GAME + URIConstants.START))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
        objectMapper.writeValueAsString(game));
  }

  @Test
  public void test_move_returnsPlayerMove() throws Exception {
    PlayerMove playerMove = new PlayerMove(player1, new DiceRoll(2));
    when(gameServiceMock.movePlayer()).thenReturn(playerMove);

    MvcResult mvcResult = mvc.perform(post(URIConstants.GAME + URIConstants.MOVE))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
        objectMapper.writeValueAsString(playerMove));
  }

  @Test
  public void test_getActivePlayer_returnsPlayer() throws Exception {
    when(gameServiceMock.getActivePlayer()).thenReturn(player1);

    MvcResult mvcResult = mvc.perform(get(URIConstants.GAME + URIConstants.ACTIVE_PLAYER))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
        objectMapper.writeValueAsString(player1));
  }

  @Test
  public void test_nextTurn_returnsNextPlayer() throws Exception {
    when(gameServiceMock.nextTurn()).thenReturn(player1);

    MvcResult mvcResult = mvc.perform(post(URIConstants.GAME + URIConstants.NEXT_TURN))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
        objectMapper.writeValueAsString(player1));
  }
}
