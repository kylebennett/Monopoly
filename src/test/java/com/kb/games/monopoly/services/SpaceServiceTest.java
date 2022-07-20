package com.kb.games.monopoly.services;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import com.kb.games.monopoly.model.spaces.Space;
import com.kb.games.monopoly.repositories.SpaceRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SpaceServiceTest {

  @Autowired
  SpaceService spaceService;

  @MockBean
  SpaceRepository spaceRepoMock;

  @Mock
  Space space1, space2, space3;

  @BeforeEach
  public void setup() throws Exception {

  }

  @Test
  public void test_SaveSpace_ReturnsSavedSpace() throws Exception {
    when(spaceRepoMock.save(space1)).thenReturn(space1);
    assertThat(spaceService.saveSpace(space1)).isEqualTo(space1);
  }

  @Test
  public void test_GetAllSpaces_returnsListOfAllSpaces() throws Exception {
    when(spaceRepoMock.findAll()).thenReturn(Arrays.asList(space1, space2, space3));
    assertThat(spaceService.getAllSpaces()).contains(space1, space2, space3);
  }

  @Test
  public void test_GetSpace_returnsSpecificSpace() throws Exception {
    when(spaceRepoMock.getById(anyLong())).thenReturn(space1);
    assertThat(spaceService.getSpace(1L)).isEqualTo(space1);
  }
}
