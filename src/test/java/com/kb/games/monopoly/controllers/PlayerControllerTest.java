package com.kb.games.monopoly.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.services.PlayerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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

    mvc.perform(get(URIConstants.PLAYERS))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(hasSize(3)))
        .andExpect(jsonPath("$.[0].name").value(equalTo(player1.getName())))
        .andExpect(jsonPath("$.[1].name").value(equalTo(player2.getName())))
        .andExpect(jsonPath("$.[2].name").value(equalTo(player3.getName())))
        .andDo(print());
  }

  @Test
  public void test_getPlayer_ReturnsSpecificPlayer() throws Exception {
    when(playerServiceMock.getPlayer(eq(1l))).thenReturn(Optional.of(player1));

    mvc.perform(get(URIConstants.PLAYERS + "/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(equalTo(player1.getName())))
        .andDo(print());
  }

  @Test
  public void test_createPlayer_returnsCreatedPlayer() throws Exception {
    when(playerServiceMock.createPlayer(any(Player.class))).thenReturn(player1);

    mvc.perform(
        post(URIConstants.PLAYERS + URIConstants.CREATE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(player1)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value(equalTo(player1.getName())))
        .andDo(print());
  }
}
