package com.kb.games.monopoly.controllers;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.games.monopoly.model.Board;
import com.kb.games.monopoly.services.BoardService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {

  @Autowired
  MockMvc mvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  BoardService boardServiceMock;

  Board board;

  @BeforeEach
  public void setUp() {
    board = new Board();
  }

  @Test
  public void test_getBoard_returnsBoard() throws Exception {
    when(boardServiceMock.getBoard()).thenReturn(board);
    MvcResult mvcResult = mvc.perform(get(URIConstants.BOARD))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
        objectMapper.writeValueAsString(board));
  }
}
