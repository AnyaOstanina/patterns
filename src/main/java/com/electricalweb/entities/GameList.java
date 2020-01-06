package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;

public class GameList extends EntityList{
        private static final List<Game> gameList = new ArrayList();

        public GameList(){
            super(gameList);
        }

        static {
            gameList.add(new Game("Basketball"));
            gameList.add(new Game("Football"));
        }

        public static List <Game> getInstance(){
            return gameList;
        }
}
