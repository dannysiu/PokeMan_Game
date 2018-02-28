package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;


/** Test for making a turning hallway between rooms */
public class TestMakeHallway_v2 {

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

        //Now for building 2 rooms in world

        Random randomGenerator = new Random();
        RoomGenerator rg = new RoomGenerator();
        while (rg.getRoomList().size() < 2) {
            int posX = RandomUtils.uniform(randomGenerator, WIDTH);
            int posY = RandomUtils.uniform(randomGenerator, HEIGHT);
            Position roomLocation = new Position(posX, posY);
            int roomWidth = RandomUtils.uniform(randomGenerator, 3, WIDTH/3);
            int roomHeight = RandomUtils.uniform(randomGenerator, 3, HEIGHT/3);
            rg.makeRoom(world, roomLocation, roomWidth, roomHeight);
        }


        // build a turning hallway to connect the rooms
        MakeHallway_v2 mh = new MakeHallway_v2();
        Room r1 = rg.getRoomList().get(0);
        Room r2 = rg.getRoomList().get(1);
        mh.buildHallway(world, r1, r2);


        ter = new TERenderer();

        ter.initialize(WIDTH, HEIGHT);

        //Draw the world to the screen
        ter.renderFrame(world);
    }

}
