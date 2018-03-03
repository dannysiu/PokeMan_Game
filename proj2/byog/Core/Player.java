package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    boolean gameOver;
    Position playerLocation;
    Random randomGenerator;
    TETile[][] worldMap;
    TERenderer renderEngine;

    public Player(Random random, TERenderer theRenderer, TETile[][] world) {

        this.randomGenerator = random;
        this.worldMap = world;
        this.renderEngine = theRenderer;
        gameOver = false;
        StdDraw.enableDoubleBuffering(); // Necessary?

        // Set Pikachu on worldMap
        StdDraw.clear(StdDraw.BLACK);
        playerLocation = setSpawn();
        worldMap[playerLocation.getX()][playerLocation.getY()] = Tileset.PIKACHU;
        renderEngine.renderFrame(worldMap);

        // Gameplay loop
        playerInput();


    }

    /** Selects a random FLOOR to spawn the player at */
    private Position setSpawn() {

        ArrayList<Position> allFloors = new ArrayList<>(25);
        Position floorMaybe;

        for (int x = 0; x < worldMap.length; x += 1) {
            for (int y = 0; y < worldMap[0].length; y += 1) {
                if (worldMap[x][y] == Tileset.FLOOR) {
                    floorMaybe = new Position(x, y);
                    allFloors.add(floorMaybe);
                }
            }
        }
        // For now, will set a spawn anywhere that is a Tileset.FLOOR
        int spawnIndex = RandomUtils.uniform(randomGenerator, allFloors.size());
        return allFloors.get(spawnIndex);
    }


    private void playerInput() {
        while (!gameOver) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }

            char command = StdDraw.nextKeyTyped();
            if (command == 'q' || command == 'Q') {
                gameOver = true;
                continue;
            } else {
                moveMaybe(command);
                StdDraw.clear(StdDraw.BLACK);
                //TODO: delete old Pikachu tileset, make sure to render new one
                renderEngine.renderFrame(worldMap);
            }

//            drawFrame(input);


        }
    }


    private void moveMaybe(char command) {
        int playX = playerLocation.getX();
        int playY = playerLocation.getY();

        if (command == 'd' || command == 'D') { // Going right
            if (worldMap[playX + 1][playY] == Tileset.FLOOR) {
                playerLocation = new Position(playX + 1, playY);
            }
        }
        if (command == 'a' || command == 'A') { // Going left
            if (worldMap[playX - 1][playY] == Tileset.FLOOR) {
                playerLocation = new Position(playX - 1, playY);
            }
        }
        if (command == 'w' || command == 'W') { // Going up
            if (worldMap[playX][playY + 1] == Tileset.FLOOR) {
                playerLocation = new Position(playX, playY + 1);
            }
        }
        if (command == 's' || command == 'S') { // Going down
            if (worldMap[playX][playY - 1] == Tileset.FLOOR) {
                playerLocation = new Position(playX, playY - 1);
            }
        }
    }

}
