package byog.Core;

import static org.junit.Assert.*;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

/** For testing the HallwayGenerator.java class. Most tests will have to be by visual inspection */
public class TestHallway {

    String scream;
    public TestHallway() {
        scream = "AHHHHH I hate this project.";
        return;
    }



    private TETile[][] TestWorldMaker(int width, int height) {
        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }



    @Test
    public void TestUnobstructed() {
        /** Tests whether unobstructed class in HallwayGenerator.java works */
        TestHallway tester = new TestHallway();
        TETile[][] world = tester.TestWorldMaker(80, 40);
        TERenderer ter = new TERenderer();
        RoomGenerator rg = new RoomGenerator();
        HallwayGenerator hg = new HallwayGenerator();

        Position roomP = new Position(30, 10);
        Position start = new Position(20, 20);
        Position end = new Position(60, 20);

        rg.makeRoom(world, roomP, 20, 20);

//        Boolean actual = rg.unobstructed(start, end, world);
//        assertFalse(actual);

    }


    /** Visual inspection test. Change width and height declared near beginning to affect world. */
    public static void main (String[] args) {
        // ~~~ Make changes to these variables to change test ~~~
        TestHallway tester = new TestHallway();
        TERenderer ter;
        TETile[][] world;
        HallwayGenerator hg = new HallwayGenerator();
        int width = 80;
        int height = 40;
        Position start = new Position(10, 10);
        Position end = new Position(40, 10);


        // ~~~ End of change zone for test ~~~
        world = tester.TestWorldMaker(width, height);

//        hg.connect(start, end, world);

        ter = new TERenderer();
        ter.initialize(width, height);

        ter.renderFrame(world);
    }

}
