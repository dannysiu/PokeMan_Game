package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.util.Random;

public class Player implements java.io.Serializable {

    private static final long serialVersionUID = 3457898234751111L;
    boolean gameOver;
    Position playerLocationP;
    Position playerLocationJ;
    Random randomGenerator;
    TETile[][] worldMap;
    TERenderer renderEngine;

    int scoreP1;
    int scoreP2;

    boolean stuck1;
    boolean stuck2;

    int stuckTime1;
    int stuckTime2;

    public Player(Random random, TERenderer theRenderer, TETile[][] world) {

        this.randomGenerator = random;
        this.worldMap = world;
        this.renderEngine = theRenderer;

        // Set Pikachu and Boss-Puff on worldMap
        StdDraw.clear(StdDraw.BLACK);
        playerLocationP = setSpawn();
        playerLocationJ = setSpawn();
        worldMap[playerLocationP.getX()][playerLocationP.getY()] = Tileset.PIKACHU;
        worldMap[playerLocationJ.getX()][playerLocationJ.getY()] = Tileset.JIGGLYPUFF;
        renderEngine.renderFrame(worldMap);
    }

    /** Version without renderer (for playing with strings) */
    public Player(Random random, TETile[][] world) {

        this.randomGenerator = random;
        this.worldMap = world;

        // Set Pikachu and Boss-Puff on worldMap
        playerLocationP = setSpawn();
        playerLocationJ = setSpawn();
        worldMap[playerLocationP.getX()][playerLocationP.getY()] = Tileset.PIKACHU;
        worldMap[playerLocationJ.getX()][playerLocationJ.getY()] = Tileset.JIGGLYPUFF;
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



    public void moveMaybe(char command) {
        int pikX = playerLocationP.getX();
        int pikY = playerLocationP.getY();
        int puffX = playerLocationJ.getX();
        int puffY = playerLocationJ.getY();


        if (stuckTime1 > 0) {
            stuckTime1 -= 1;
        } else {
            stuck1 = false;
        }

        // Pikachu (player 1) movements are below
        if (!stuck1) {

            if (command == 'd' || command == 'D') { // Going right
                if (!(worldMap[pikX + 1][pikY].equals(Tileset.WATER)) && !(worldMap[pikX + 1][pikY].equals(Tileset.WALL))) {
                    if (worldMap[pikX + 1][pikY].equals(Tileset.RAZZBERRY)) {
                        scoreP1 += 5;
                    } else if (worldMap[pikX + 1][pikY].equals(Tileset.PINAPBERRY)) {
                        scoreP1 += 10;
                    } else if (worldMap[pikX + 1][pikY].equals(Tileset.POKEBALL)) {
                        scoreP1 -= 2;
                        stuck1 = true;
                        stuckTime1 = 5;
                    }
                    playerLocationP = new Position(pikX + 1, pikY);
                    worldMap[pikX][pikY] = Tileset.FLOOR; // Change old player position back to floor
                    worldMap[playerLocationP.getX()][playerLocationP.getY()] = Tileset.PIKACHU;
                }
            }
            if (command == 'a' || command == 'A') { // Going left
                if (!(worldMap[pikX - 1][pikY].equals(Tileset.WATER)) && !(worldMap[pikX - 1][pikY].equals(Tileset.WALL))) {
                    if (worldMap[pikX - 1][pikY].equals(Tileset.RAZZBERRY)) {
                        scoreP1 += 5;
                    } else if (worldMap[pikX - 1][pikY].equals(Tileset.PINAPBERRY)) {
                        scoreP1 += 10;
                    } else if (worldMap[pikX - 1][pikY].equals(Tileset.POKEBALL)) {
                        scoreP1 -= 2;
                        stuck1 = true;
                        stuckTime1 = 5;
                    }
                    playerLocationP = new Position(pikX - 1, pikY);
                    worldMap[pikX][pikY] = Tileset.FLOOR; // Change old player position back to floor
                    worldMap[playerLocationP.getX()][playerLocationP.getY()] = Tileset.PIKACHU;
                }
            }
            if (command == 'w' || command == 'W') { // Going up
                if (!(worldMap[pikX][pikY + 1].equals(Tileset.WATER)) && !(worldMap[pikX][pikY + 1].equals(Tileset.WALL))) {
                    if (worldMap[pikX][pikY + 1].equals(Tileset.RAZZBERRY)) {
                        scoreP1 += 5;
                    } else if (worldMap[pikX][pikY + 1].equals(Tileset.PINAPBERRY)) {
                        scoreP1 += 10;
                    } else if (worldMap[pikX][pikY + 1].equals(Tileset.POKEBALL)) {
                        scoreP1 -= 2;
                        stuck1 = true;
                        stuckTime1 = 5;
                    }
                    playerLocationP = new Position(pikX, pikY + 1);
                    worldMap[pikX][pikY] = Tileset.FLOOR; // Change old player position back to floor
                    worldMap[playerLocationP.getX()][playerLocationP.getY()] = Tileset.PIKACHU;
                }
            }
            if (command == 's' || command == 'S') { // Going down
                if (!(worldMap[pikX][pikY - 1].equals(Tileset.WATER)) && !(worldMap[pikX][pikY - 1].equals(Tileset.WALL))) {
                    if (worldMap[pikX][pikY - 1].equals(Tileset.RAZZBERRY)) {
                        scoreP1 += 5;
                    } else if (worldMap[pikX][pikY - 1].equals(Tileset.PINAPBERRY)) {
                        scoreP1 += 10;
                    } else if (worldMap[pikX][pikY - 1].equals(Tileset.POKEBALL)) {
                        scoreP1 -= 2;
                        stuck1 = true;
                        stuckTime1 = 5;
                    }
                    playerLocationP = new Position(pikX, pikY - 1);
                    worldMap[pikX][pikY] = Tileset.FLOOR; // Change old player position back to floor
                    worldMap[playerLocationP.getX()][playerLocationP.getY()] = Tileset.PIKACHU;
                }
            }
        }


        if (stuckTime2 > 0) {
            stuckTime2 -= 1;
        } else {
            stuck2 = false;
        }

        // Boss-Puff movements (player 2) are below
        if (!stuck2) {
            if (command == 'l' || command == 'L') { // Going right
                if (!worldMap[puffX + 1][puffY].equals(Tileset.WATER) && !worldMap[puffX + 1][puffY].equals(Tileset.WALL)) {
                    if (worldMap[puffX + 1][puffY].equals(Tileset.RAZZBERRY)) {
                        scoreP2 += 5;
                    } else if (worldMap[puffX + 1][puffY].equals(Tileset.PINAPBERRY)) {
                        scoreP2 += 10;
                    } else if (worldMap[puffX + 1][puffY].equals(Tileset.POKEBALL)) {
                        scoreP2 -= 2;
                        stuck2 = true;
                        stuckTime2 = 5;
                    }
                    playerLocationJ = new Position(puffX + 1, puffY);
                    worldMap[puffX][puffY] = Tileset.FLOOR; // Change old player position back to floor
                    worldMap[playerLocationJ.getX()][playerLocationJ.getY()] = Tileset.JIGGLYPUFF;
                }
            }
            if (command == 'j' || command == 'J') { // Going left
                if (!worldMap[puffX - 1][puffY].equals(Tileset.WATER) && !worldMap[puffX - 1][puffY].equals(Tileset.WALL)) {
                    if (worldMap[puffX - 1][puffY].equals(Tileset.RAZZBERRY)) {
                        scoreP2 += 5;
                    } else if (worldMap[puffX - 1][puffY].equals(Tileset.PINAPBERRY)) {
                        scoreP2 += 10;
                    } else if (worldMap[puffX - 1][puffY].equals(Tileset.POKEBALL)) {
                        scoreP2 -= 2;
                        stuck2 = true;
                        stuckTime2 = 5;
                    }
                    playerLocationJ = new Position(puffX - 1, puffY);
                    worldMap[puffX][puffY] = Tileset.FLOOR; // Change old player position back to floor
                    worldMap[playerLocationJ.getX()][playerLocationJ.getY()] = Tileset.JIGGLYPUFF;
                }
            }
            if (command == 'i' || command == 'I') { // Going up
                if (!worldMap[puffX][puffY + 1].equals(Tileset.WATER) && !worldMap[puffX][puffY + 1].equals(Tileset.WALL)) {
                    if (worldMap[puffX][puffY + 1].equals(Tileset.RAZZBERRY)) {
                        scoreP2 += 5;
                    } else if (worldMap[puffX][puffY + 1].equals(Tileset.PINAPBERRY)) {
                        scoreP2 += 10;
                    } else if (worldMap[puffX][puffY + 1].equals(Tileset.POKEBALL)) {
                        scoreP2 -= 2;
                        stuck2 = true;
                        stuckTime2 = 5;
                    }
                    playerLocationJ = new Position(puffX, puffY + 1);
                    worldMap[puffX][puffY] = Tileset.FLOOR; // Change old player position back to floor
                    worldMap[playerLocationJ.getX()][playerLocationJ.getY()] = Tileset.JIGGLYPUFF;
                }
            }
            if (command == 'k' || command == 'K') { // Going down
                if (!worldMap[puffX][puffY - 1].equals(Tileset.WATER) && !worldMap[puffX][puffY - 1].equals(Tileset.WALL)) {
                    if (worldMap[puffX][puffY - 1].equals(Tileset.RAZZBERRY)) {
                        scoreP2 += 5;
                    } else if (worldMap[puffX][puffY - 1].equals(Tileset.PINAPBERRY)) {
                        scoreP2 += 10;
                    } else if (worldMap[puffX][puffY - 1].equals(Tileset.POKEBALL)) {
                        scoreP2 -= 2;
                        stuck2 = true;
                        stuckTime2 = 5;
                    }
                    playerLocationJ = new Position(puffX, puffY - 1);
                    worldMap[puffX][puffY] = Tileset.FLOOR; // Change old player position back to floor
                    worldMap[playerLocationJ.getX()][playerLocationJ.getY()] = Tileset.JIGGLYPUFF;
                }
            }
        }
    }

}
