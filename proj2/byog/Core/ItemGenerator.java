package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

/** much of this class is similar to the RoomGenerator class, for reference */

public class ItemGenerator {
    Random randomGenerator;
    private static int fruitCount;
    private static int trapCount;
    private static int obstacleCount;

    /**
     * Object will be used to add items to within the rooms and hallways
     */
    public ItemGenerator(Random random) {
        this.randomGenerator = random;
        fruitCount = 0;
        trapCount = 0;
    }

    /** Randomly place fruit within the world */
    public void addFruit(TETile[][] world) {
        int worldWidth = world.length;
        int worldHeight = world[0].length;

        while (fruitCount < 25) {
            int posX = RandomUtils.uniform(randomGenerator, worldWidth);
            int posY = RandomUtils.uniform(randomGenerator, worldHeight);
//            Position fruitLocation = new Position(posX, posY);

            if (world[posX][posY] == Tileset.FLOOR) {
                world[posX][posY] = Tileset.RAZZBERRY;
                fruitCount += 1;
            }
        }
        while (fruitCount < 35) {
            int posX = RandomUtils.uniform(randomGenerator, worldWidth);
            int posY = RandomUtils.uniform(randomGenerator, worldHeight);
//            Position fruitLocation = new Position(posX, posY);

            if (world[posX][posY] == Tileset.FLOOR) {
                world[posX][posY] = Tileset.PINAPBERRY;
                fruitCount += 1;
            }
        }

    }

    /** Randomly place traps within the world */
    public void addTraps(TETile[][] world) {
        int worldWidth = world.length;
        int worldHeight = world[0].length;

        while (trapCount < 25) {
            int posX = RandomUtils.uniform(randomGenerator, worldWidth);
            int posY = RandomUtils.uniform(randomGenerator, worldHeight);
//            Position fruitLocation = new Position(posX, posY);

            if (world[posX][posY] == Tileset.FLOOR) {
                world[posX][posY] = Tileset.POKEBALL;
                trapCount += 1;
            }
        }
    }

    /** Randomly place obstacles within the world */
    public void addObstacles(TETile[][] world) {
        int worldWidth = world.length;
        int worldHeight = world[0].length;

        while (obstacleCount < 25) {
            int posX = RandomUtils.uniform(randomGenerator, worldWidth);
            int posY = RandomUtils.uniform(randomGenerator, worldHeight);
//            Position fruitLocation = new Position(posX, posY);

            if (world[posX][posY] == Tileset.FLOOR) {
                world[posX][posY] = Tileset.WATER;
                obstacleCount += 1;
            }
        }
    }
}
