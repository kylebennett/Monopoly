package com.kb.games.monopoly.repositories;

import com.kb.games.monopoly.model.Space;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
}
