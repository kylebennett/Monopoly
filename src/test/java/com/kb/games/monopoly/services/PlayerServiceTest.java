package com.kb.games.monopoly.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kb.games.monopoly.model.Player;
import com.kb.games.monopoly.repositories.PlayerRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PlayerServiceTest {

  @Autowired
  PlayerService playerService;

  @MockBean
  PlayerRepository playerRepoMock;

  @Mock
  Player player1, player2;

  @Test
  public void test_getAllPlayers_returnsPlayerList() throws Exception {

    List<Player> playerList = new ArrayList<>();
    playerList.add(player1);
    playerList.add(player2);

    when(playerRepoMock.findAll()).thenReturn(playerList);

    assertThat(playerService.getAllPlayers());
  }

  @Test
  public void test_getPlayer_returnsPlayer() throws Exception {
    Optional<Player> expected = Optional.of(player1);
    when(playerRepoMock.findById(eq(1L))).thenReturn(expected);

    assertThat(playerService.getPlayer(1L)).isEqualTo(expected);
  }

  @Test
  public void test_createPlayer_savesPlayer() throws Exception {
    when(playerRepoMock.save(any(Player.class))).thenReturn(player1);

    assertThat(playerService.createPlayer(player1)).isEqualTo(player1);
    verify(playerRepoMock, times(1)).save(player1);
  }

}
