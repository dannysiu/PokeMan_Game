package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    boolean gameOver;
    Position playerLocationP;
    Position playerLocationJ;
    Random randomGenerator;
    TETile[][] worldMap;
    TERenderer renderEngine;

    public Player(Random random, TERenderer theRenderer, TETile[][] world) {

        this.randomGenerator = random;
        this.worldMap = world;
        this.renderEngine = theRenderer;
        gameOver = false;
//        StdDraw.enableDoubleBuffering(); // Necessary? - doesn't seem like it

        // Set Pikachu and Boss-Puff on worldMap
        StdDraw.clear(StdDraw.BLACK);
        playerLocationP = setSpawn();
        playerLocationJ = setSpawn();
        worldMap[playerLocationP.getX()][playerLocationP.getY()] = Tileset.PIKACHU;
        worldMap[playerLocationJ.getX()][playerLocationJ.getY()] = Tileset.JIGGLYPUFF;
        renderEngine.renderFrame(worldMap);

        // For purpose of testing
//        playerInput();

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


    // Deprecated: moved gameplay loop into Game.java class
    private void playerInput() {
        while (!gameOver) {

            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }

            char command = StdDraw.nextKeyTyped();
            if (command == 'q' || command == 'Q') {
                gameOver = true;
                System.exit(0);
            } else {
                moveMaybe(command);
                StdDraw.clear(StdDraw.BLACK);
                renderEngine.renderFrame(worldMap);
            }
        }
    }


    public void moveMaybe(char command) {
        int pikX = playerLocationP.getX();
        int pikY = playerLocationP.getY();
        int puffX = playerLocationJ.getX();
        int puffY = playerLocationJ.getY();
        
        // Pikachu (player 1) movements are below
        if (command == 'd' || command == 'D') { // Going right
            if (worldMap[pikX + 1][pikY] == Tileset.FLOOR) {
                playerLocationP = new Position(pikX + 1, pikY);
                worldMap[pikX][pikY] = Tileset.FLOOR; // Change old player position back to floor
                worldMap[playerLocationP.getX()][playerLocationP.getY()] = Tileset.PIKACHU;
            }
        }
        if (command == 'a' || command == 'A') { // Going left
            if (worldMap[pikX - 1][pikY] == Tileset.FLOOR) {
                playerLocationP = new Position(pikX - 1, pikY);
                worldMap[pikX][pikY] = Tileset.FLOOR; // Change old player position back to floor
                worldMap[playerLocationP.getX()][playerLocationP.getY()] = Tileset.PIKACHU;
            }
        }
        if (command == 'w' || command == 'W') { // Going up
            if (worldMap[pikX][pikY + 1] == Tileset.FLOOR) {
                playerLocationP = new Position(pikX, pikY + 1);
                worldMap[pikX][pikY] = Tileset.FLOOR; // Change old player position back to floor
                worldMap[playerLocationP.getX()][playerLocationP.getY()] = Tileset.PIKACHU;
            }
        }
        if (command == 's' || command == 'S') { // Going down
            if (worldMap[pikX][pikY - 1] == Tileset.FLOOR) {
                playerLocationP = new Position(pikX, pikY - 1);
                worldMap[pikX][pikY] = Tileset.FLOOR; // Change old player position back to floor
                worldMap[playerLocationP.getX()][playerLocationP.getY()] = Tileset.PIKACHU;
            }
        }
        
        // Boss-Puff movements (player 2) are below
        if (command == 'l' || command == 'L') { // Going right
            if (worldMap[puffX + 1][puffY] == Tileset.FLOOR) {
                playerLocationJ = new Position(puffX + 1, puffY);
                worldMap[puffX][puffY] = Tileset.FLOOR; // Change old player position back to floor
                worldMap[playerLocationJ.getX()][playerLocationJ.getY()] = Tileset.JIGGLYPUFF;
            }
        }
        if (command == 'j' || command == 'J') { // Going left
            if (worldMap[puffX - 1][puffY] == Tileset.FLOOR) {
                playerLocationJ = new Position(puffX - 1, puffY);
                worldMap[puffX][puffY] = Tileset.FLOOR; // Change old player position back to floor
                worldMap[playerLocationJ.getX()][playerLocationJ.getY()] = Tileset.JIGGLYPUFF;
            }
        }
        if (command == 'i' || command == 'I') { // Going up
            if (worldMap[puffX][puffY + 1] == Tileset.FLOOR) {
                playerLocationJ = new Position(puffX, puffY + 1);
                worldMap[puffX][puffY] = Tileset.FLOOR; // Change old player position back to floor
                worldMap[playerLocationJ.getX()][playerLocationJ.getY()] = Tileset.JIGGLYPUFF;
            }
        }
        if (command == 'k' || command == 'K') { // Going down
            if (worldMap[puffX][puffY - 1] == Tileset.FLOOR) {
                playerLocationJ = new Position(puffX, puffY - 1);
                worldMap[puffX][puffY] = Tileset.FLOOR; // Change old player position back to floor
                worldMap[playerLocationJ.getX()][playerLocationJ.getY()] = Tileset.JIGGLYPUFF;
            }
        }
    }

}
