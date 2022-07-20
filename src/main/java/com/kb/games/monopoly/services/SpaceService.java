package com.kb.games.monopoly.services;

import java.util.List;

import com.kb.games.monopoly.model.spaces.Space;
import com.kb.games.monopoly.repositories.SpaceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceService {

  @Autowired
  private SpaceRepository spaceRepo;

  public Space saveSpace(Space space) {
    return spaceRepo.save(space);
  }

  public List<Space> getAllSpaces() {
    return spaceRepo.findAll();
  }

  public Space getSpace(Long id) {
    return spaceRepo.getById(id);
  }
}
