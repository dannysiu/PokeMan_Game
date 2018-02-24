package byog.Core;

import byog.Core.WorldGenerator;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/** A class to handle hallway generation.
 *  Assumes that rooms are already created and randomly dispersed in world. Will look for
 *  "doorways" (unlocked doors) and create hallways between them.
 *  TODO: Method to create horizontal hallways.
 *  TODO: Method to create vertical hallways.
 *  TODO: Method to create corners.
 *  TODO: Method to link hallways together.
 *  TODO: Create a test class to assess functionality.
 */
public class HallwayGenerator {

    /** Creates a hallway to connect two unlocked doors. Calls horizontal, vertical,
     *  and corner contructors as necessary.
     */
    public HallwayGenerator(position one, position two, TETile[][] world) { // Position class not compiling?
        // TODO: Algorithim to select what hallways needed to connect two positions.

    }

    /** Creates a horizonatal hallway. */
    private void horizontalHallway(int length, int horizPos, int vertPos, TETile[][] world) {

        for (int i = horizPos; i < (horizPos + length); i += 1) {
            world[i][vertPos] = Tileset.FLOOR;
            world[i][vertPos - 1] = Tileset.WALL;
            world[i][vertPos + 1] = Tileset.WALL;
        }
    }


    /** Creates a vertical hallway. */
    private void verticalHallway(int height, int horizPos, int vertPos, TETile[][] world) {

        for (int i = vertPos; i < (vertPos + height); i += 1) {
            world[horizPos][i] = Tileset.FLOOR;
            world[horizPos - 1][i] = Tileset.WALL;
            world[horizPos + 1][i] = Tileset.WALL;
        }
    }

    /** Creates an L corner connecting hallways. */
    private class cornerHallway {



    }

}
