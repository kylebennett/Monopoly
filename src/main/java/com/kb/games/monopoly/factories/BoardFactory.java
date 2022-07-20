package com.kb.games.monopoly.factories;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.kb.games.monopoly.model.Board;
import com.kb.games.monopoly.model.spaces.CornerSpace;
import com.kb.games.monopoly.model.spaces.Space;
import com.kb.games.monopoly.model.spaces.StreetSpace;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BoardFactory {

  private static final String DEFAULT_JSON_LOCATION = "./src/main/resources/boards/default.json";

  public Board loadDefaultBoard() throws FileNotFoundException {

    Board board = new Board();
    List<Space> spaces = new ArrayList<Space>();

    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new FileReader(DEFAULT_JSON_LOCATION));

    JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();

    for (JsonElement jsonElement : array) {
      System.out.println(jsonElement);
      String type = jsonElement.getAsJsonObject().get("type").getAsString();
      JsonObject space = jsonElement.getAsJsonObject().getAsJsonObject("space");

      switch (type) {
        case "CORNER":
          CornerSpace corner = gson.fromJson(space, CornerSpace.class);
          spaces.add(corner);
          break;

        case "STREET":
          StreetSpace street = gson.fromJson(space, StreetSpace.class);
          spaces.add(street);
          break;

        default:
          System.out.println();
      }
    }
    board.setSpaces(spaces);
    return board;
  }
}
