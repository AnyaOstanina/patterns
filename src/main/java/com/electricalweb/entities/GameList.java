package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;

public class GameList {
        private static final List<Game> gameList = new ArrayList();

        private GameList(){
        }

        static {
            gameList.add(new Game("Basketball"));
            gameList.add(new Game("Football"));
        }

        public static List <Game> getInstance(){
            return gameList;
        }
}
