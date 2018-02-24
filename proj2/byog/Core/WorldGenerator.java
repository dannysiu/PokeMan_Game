package byog.Core;

import byog.TileEngine.TETile;

public class WorldGenerator {


    /** Creates an array of all unlocked doors in the world (signifying places where rooms need
     *  to be connected via hallways.
     *  TODO: replace array of Positions with a linkedlist.
     *  TODO: find a way to replace 80 and 30 ints with dynamic world size.
     *  TODO: find a way to check with .equals if a tile is an UNLOCKED_DOOR variant.
     * @param world
     * @return
     */
    private TETile[] unlockedFinder(TETile[][] world) {
        Position[] allUnlocked = new Position[100]; // Would rather use a linkedlist. Is that in library?

        for (int x; x < 80; x += 1) { // Using 80 for width and 30 for height because of default values
            for (int y; y < 30; y += 1) { // that Game.java uses to make a world
                if (world[x][y].equals(TETile.UNLOCKED_DOOR)) {
                    Position p = new Position(x, y);
                    allUnlocked[x] = p;
                }
            }
        }

    }

}
