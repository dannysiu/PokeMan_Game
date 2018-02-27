package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;


public class TestRoomsWHallways {

    /** Visual inspection test. Check that rooms and doorways are made correctly.
     *  Simulate making many rooms to save time when checking and check room interactions.
     *  */
    public static void main (String[] args) {
        //Draw the screen
        TERenderer ter;
        int WIDTH = 50;
        int HEIGHT = 30;

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        //Now for building rooms in world

        Random randomGenerator = new Random();


        RoomGenerator rg = new RoomGenerator();
        for (int i = 0; i < 50; i += 1) {   //make up to 50 rooms in the world; some will overlap and fail to exist
            int posX = RandomUtils.uniform(randomGenerator, WIDTH);
            int posY = RandomUtils.uniform(randomGenerator, HEIGHT);
            Position roomLocation = new Position(posX, posY);
            int roomWidth = RandomUtils.uniform(randomGenerator, 3, WIDTH/3);
            int roomHeight = RandomUtils.uniform(randomGenerator, 3, HEIGHT/3);
            rg.makeRoom(world, roomLocation, roomWidth, roomHeight);
        }

        // doors no longer used in this approach
//        for (Room r : rg.getRoomList()) {  //add doors to all the rooms that exist
//            r.makeDoors(world, randomGenerator);
//
//        }


        HallwayGenerator hg = new HallwayGenerator();

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






        ter = new TERenderer();

        ter.initialize(WIDTH, HEIGHT);

        //Draw the world to the screen
        ter.renderFrame(world);
    }

}
