package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

/** much of this class is similar to the RoomGenerator class, for reference */

public class ItemGenerator {
    Random randomGenerator;
    private ArrayList fruitList;
    private static int fruitCount;

    /**
     * Object will be used to add items to within the rooms and hallways
     */
    public ItemGenerator(Random random) {
        this.randomGenerator = random;
        fruitList = new ArrayList(5);
        fruitCount = 0;
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
                world[posX][posY] = Tileset.FRUIT;
                fruitList.add("fruit");
                fruitCount += 1;
            }
        }
    }
}
