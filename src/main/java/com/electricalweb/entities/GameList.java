package com.electricalweb.entities;
import com.electricalweb.interfaces.IEntity;
import com.electricalweb.interfaces.IEntityList;

import java.util.ArrayList;
import java.util.List;

public class GameList extends EntityList implements IEntityList {
        private static final List<IEntity> gameList = new ArrayList();

        public GameList(){
            super(gameList);
        }

        static {
            gameList.add(new Game("Basketball"));
            gameList.add(new Game("Football"));
        }

        @Override
        public List <IEntity> getInstance(){
            return gameList;
        }
}
