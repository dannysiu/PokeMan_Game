package byog.Core;

import byog.TileEngine.TETile;
import static byog.TileEngine.Tileset.*;

public class WorldGenerator {


    /** Creates an array of all unlocked doors in the world (signifying places where rooms need
     *  to be connected via hallways).
     *  TODO: replace array of Positions with a linkedlist.
     *  TODO: find a way to replace 80 and 30 ints with dynamic world size.
     */
    private Position[] unlockedFinder(TETile[][] world) {
        Position[] allUnlocked = new Position[1000]; // Arbitrarily long array
        int index = 0;

        for (int x; x < 80; x += 1) { // Using 80 for width and 30 for height because of default values
            for (int y; y < 30; y += 1) { // that Game.java uses to make a world
                if (world[x][y].equals(UNLOCKED_DOOR)) {
                    Position p = new Position(x, y);
                    allUnlocked[index] = p;
                    index += 1; // Index only increments as UNLOCKED_DOOR positions added
                }
            }
        }
        Position[] skimmedAllUnlocked = new Position[index];
        System.arraycopy(allUnlocked, 0, skimmedAllUnlocked, 0, index);
        return skimmedAllUnlocked; // Return a shorter array of just the desired Positions.
    }

}
