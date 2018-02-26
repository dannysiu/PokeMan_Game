package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.List;

public class WorldGenerator {


    /** Creates a List of all unlocked doors in the world (signifying places where rooms need
     *  to be connected via hallways).
     *  TODO: find a way to replace 80 and 30 ints with dynamic world size.
     */
    private List<Position> unlockedFinder(TETile[][] world) {
        List<Position> allUnlocked = new ArrayList<>();
//        int index = 0;
//        int xPos;
//        int yPos;

        for (int xPos = 0; xPos < 80; xPos += 1) { // Using 80 for width and 30 for height because of default values
            for (int yPos = 0; yPos < 30; yPos += 1) { // that Game.java uses to make a world
                if (world[xPos][yPos].equals(Tileset.UNLOCKED_DOOR)) {
                    Position p = new Position(xPos, yPos);
                    allUnlocked.add(p);
//                    index += 1; // Index only increments as UNLOCKED_DOOR positions added
                }
            }
        }
//        Position[] skimmedAllUnlocked = new Position[index];
//        System.arraycopy(allUnlocked, 0, skimmedAllUnlocked, 0, index);
        return allUnlocked; // Return a shorter array of just the desired Positions.
    }

}
