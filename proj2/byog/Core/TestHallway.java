package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.util.Random;

/** For testing the HallwayGenerator.java class. Most tests will have to be by visual inspection */
public class TestHallway {

    String scream;
    public TestHallway() {
        scream = "AHHHHH I hate this project.";
        return;
    }



    private TETile[][] testWorldMaker(int width, int height) {
        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    private TETile[][] testWorldMakerWithBorder(int width, int height) {
        TETile[][] world = testWorldMaker(width, height);

        for (int x = 0; x < width; x += 1) {
            world[x][0] = Tileset.MOUNTAIN;
            world[x][height - 1] = Tileset.MOUNTAIN;
        }
        for (int y = 0; y < height; y += 1) {
            world[0][y] = Tileset.MOUNTAIN;
            world[width - 1][y] = Tileset.MOUNTAIN;
        }

        return world;
    }


    ///////////////////Tests live down here///////////////////////


//    @Test
//    private void FirstTestIWrote() {
//        /** Forgot what this test was supposed to do. Moved out of main method
//         *  in order to clean up.
//         *  I believe this test corresponds to an older version of HallwayGenerator, no
//         *  longer works as originally written.
//         */
//
//        // ~~~ Make changes to these variables to change test ~~~
//        TestHallway tester = new TestHallway();
//        TERenderer ter;
//        TETile[][] world;
//        HallwayGenerator hg = new HallwayGenerator();
//        int width = 80;
//        int height = 40;
//        Position start = new Position(10, 10);
//        Position end = new Position(40, 10);
//
//
//        // ~~~ End of change zone for test ~~~
//        world = tester.testWorldMaker(width, height);
//
////        hg.connect(start, end, world);
//
//        ter = new TERenderer();
//        ter.initialize(width, height);
//        ter.renderFrame(world);
//    }


//    @Test
//    private void testUnobstructed() {
//        /** Tests whether unobstructed method in Position class works */
//        TestHallway tester = new TestHallway();
//        TETile[][] world = tester.testWorldMaker(80, 40);
//        TERenderer ter = new TERenderer();
//        RoomGenerator rg = new RoomGenerator();
////        HallwayGenerator hg = new HallwayGenerator();
//
//        Position roomP = new Position(30, 10);
//        Position start = new Position(29, 10);
//        Position end = new Position(28, 10); // No longer use 2 positions to make hallway
//
//        rg.makeRoom(world, roomP, 20, 20);
//
//        Boolean actual = start.unobstructedHallway(roomP, world);
//        assertFalse(actual);
//
//    }

    @Test
    public void testBuildHallway() {
        /** Tests if buildHallway method in HallwayGenerator successfully builds desired hallway.
         *  Will use a world with a mountain perimeter to have a visual reference.
         */
        int width = 80;
        int height = 40;
        TestHallway tester = new TestHallway();
        TETile[][] world = tester.testWorldMakerWithBorder(width, height);
        TERenderer ter = new TERenderer();
        Random random = new Random();
        HallwayGenerator hg = new HallwayGenerator(random);

        Position startRight = new Position(1, 20);
        Position startLeft = new Position(16, 30);
        Position startUp = new Position(30, 1);
        Position startDown = new Position(45, 32);
//        Position shouldFail = new Position(20, 35);
        Position cornerAxis = new Position(55, 25);


        hg.buildHallway(startRight, 20, "right", world);
        hg.buildHallway(startLeft, 15, "left", world);
        hg.buildHallway(startUp, 7, "up", world);
        hg.buildHallway(startDown, 31, "down", world);
//        hg.buildHallway(shouldFail, 15, "up", world);
        hg.buildHallway(cornerAxis, 0, "rightUp", world);

        ter.initialize(width, height + 2, 0, 0);
        ter.renderFrame(world);
    }




    ////////////////////////// MAIN METHOD ///////////////////////////////

    /** Visual inspection test. Change width and height declared near beginning to affect world. */
    public static void main(String[] args) {

        TestHallway tester = new TestHallway();

        // Individual tests above. Comment out as needed
        tester.testBuildHallway();
//        tester.TestWhereToNext();
//        tester.TestWhereToNextTurn();

    }

}
