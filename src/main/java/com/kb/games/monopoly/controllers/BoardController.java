package com.kb.games.monopoly.controllers;

import com.kb.games.monopoly.model.Board;
import com.kb.games.monopoly.services.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URIConstants.BOARD)
public class BoardController {

  @Autowired
  private BoardService boardService;

  /**
   * Get the game board
   *
   * @return Board
   */
  @GetMapping
  public ResponseEntity<Board> getBoard() {

    Board board = boardService.getBoard();

    return ResponseEntity.ok(board);
  }

}
