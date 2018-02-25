package byog.Core;

import byog.Core.HallwayGenerator;
import static org.junit.Assert.*;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

/** For testing the HallwayGenerator.java class. Most tests will have to be by visual inspection */
public class TestHallway {

//    /** Create a world to be used in tests */
//    public TETile[][] TestWorldMaker(int width, int height) {
//        TETile[][] world = new TETile[width][height];
//        for (int x = 0; x < width; x += 1) {
//            for (int y = 0; y < height; y += 1) {
//                world[x][y] = Tileset.NOTHING;
//            }
//        }
//        ter = new TERenderer();
//        ter.initialize(width, height);
//        return world;
//    }

    /** Visual inspection test. Change width and height declared near beginning to affect world. */
    public static void main (String[] args) {
        TERenderer ter;
        int width = 80;
        int height = 30;

        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // Now for testing hallways. 




        ter = new TERenderer();
        ter.initialize(width, height);

        ter.renderFrame(world);
    }

}
