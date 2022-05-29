package com.kb.games.monopoly.services;

import com.kb.games.monopoly.model.Board;
import com.kb.games.monopoly.repositories.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

  @Autowired
  BoardRepository boardRepo;

  public Board getBoard() {
    return boardRepo.findAll().get(0);
  }
}
