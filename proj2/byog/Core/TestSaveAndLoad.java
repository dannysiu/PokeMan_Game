package byog.Core;

import byog.TileEngine.TETile;

import java.util.Random;

public class TestSaveAndLoad {
/** For testing the save and load functionality of the game */


    public static GameState getSavedGameIfWorks() {
        return SaveAndLoad.loadGame();
    }


    public static void main(String[] args) {

        // Change seed here
        Random random = new Random(343289473);

        TETile[][] world = Game.initializeWorld();

        GameState testGame;
        // For making a new game
        testGame = Game.playNewGame(random, world);
        // For loading a previous game
//        testGame = getSavedGameIfWorks();


        testGame.gameLoop();

    }
}
