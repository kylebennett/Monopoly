package com.kb.games.monopoly.controllers;

import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.services.PlayerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

  @Autowired
  MockMvc mvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  PlayerService playerServiceMock;

  Player player1, player2, player3;
  List<Player> playerList;

  @BeforeEach
  public void setUp() {
    player1 = new Player("Bob");
    player2 = new Player("Sally");
    player3 = new Player("Trevor");

    playerList = new ArrayList<>(Arrays.asList(player1, player2, player3));
  }

  @Test
  public void test_getAllPlayers_returnsPlayerList() throws Exception {
    when(playerServiceMock.getAllPlayers()).thenReturn(playerList);

    MvcResult mvcResult = mvc.perform(get(URIConstants.PLAYERS))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
        objectMapper.writeValueAsString(playerList));
  }

  @Test
  public void test_getPlayer_ReturnsSpecificPlayer() throws Exception {
    when(playerServiceMock.getPlayer(eq(1l))).thenReturn(Optional.of(player1));

    MvcResult mvcResult = mvc.perform(get(URIConstants.PLAYERS + "/1"))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
        objectMapper.writeValueAsString(player1));
  }

  @Test
  public void test_createPlayer_returnsCreatedPlayer() throws Exception {
    when(playerServiceMock.createPlayer(any(Player.class))).thenReturn(player1);

    MvcResult mvcResult = mvc.perform(
        post(URIConstants.PLAYERS + URIConstants.CREATE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(player1)))
        .andExpect(status().isCreated())
        .andDo(print())
        .andReturn();

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
        objectMapper.writeValueAsString(player1));
  }
}
