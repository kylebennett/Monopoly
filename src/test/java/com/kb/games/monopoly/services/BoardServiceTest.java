package com.kb.games.monopoly.services;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import com.kb.games.monopoly.model.Board;
import com.kb.games.monopoly.repositories.BoardRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardServiceTest {

  @Autowired
  BoardService boardService;

  @MockBean
  BoardRepository boardRepoMock;

  Board board;

  @BeforeEach
  public void setup() {
    board = new Board();
  }

  @Test
  public void test_GetBoard_ReturnsBoard() throws Exception {
    when(boardRepoMock.findAll()).thenReturn(Arrays.asList(board));

    assertThat(boardService.getBoard()).isEqualTo(board);
  }

}
