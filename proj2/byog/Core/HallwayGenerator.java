package byog.Core;

import byog.Core.WorldGenerator;
import byog.TileEngine.TETile;

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
    public HallwayGenerator(position one, position two) { // Position class not compiling?
        // TODO: Algorithim to select what hallways needed to connect two posiitons.

    }

    /** Creates a horizonatal hallway. */
    private class horizontalHallway {
        int length;
        int height = 3;

        private horizontalHallway(int length, int horizPos, int vertPos) {

            TETile[][] hallway = new TETile[length][height];

        }


    }

    /** Creates a vertical hallway. */
    private class verticalHallway {



    }

    /** Creates an L corner connecting hallways. */
    private class cornerHallway {



    }

}
