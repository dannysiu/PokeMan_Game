package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.List;

public class WorldGenerator {
    /** Our plan for how to approach WorldGeneration algorithm.
     *  @Source http://pcg.wikidot.com/pcg-algorithm:dungeon-generation
     *  Use a branching tree algorithm:
     *  Start by creating a single room and then branching hallways off of that room going for a
     *  random distance in a direction and then deciding whether to continue, change directions,
     *  dead end, or create a new room at the end of that hallway.
     *  TODO: implement some sort of stop condition so that we don't use all of the dead space.
     *  TODO: figure out what to do about the unlocked doors at the end of world generation
     */



    /** Creates a List of all unlocked doors in the world (signifying places where rooms need
     *  to be connected via hallways).
     *  TODO: find a way to replace 80 and 30 ints with dynamic world size.
     */
    private List<Position> unlockedFinder(TETile[][] world) {
        List<Position> allUnlocked = new ArrayList<>();

        for (int xPos = 0; xPos < 80; xPos += 1) { // Using 80 for width and 30 for height because of default values
            for (int yPos = 0; yPos < 30; yPos += 1) { // that Game.java uses to make a world
                if (world[xPos][yPos].equals(Tileset.UNLOCKED_DOOR)) {
                    Position p = new Position(xPos, yPos);
                    allUnlocked.add(p);
                }
            }
        }
        return allUnlocked;
    }

}
