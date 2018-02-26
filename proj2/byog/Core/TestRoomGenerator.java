package byog.Core;

import byog.Core.HallwayGenerator;
import static org.junit.Assert.*;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;
import java.util.Random;

public class TestRoomGenerator {
    /** Visual inspection test. Change width and height declared near beginning to affect world. */
    public static void main (String[] args) {
        //Draw the screen
        TERenderer ter;
        int WIDTH = 80;
        int HEIGHT = 50;

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
            int roomWidth = RandomUtils.uniform(randomGenerator, WIDTH/3);
            int roomHeight = RandomUtils.uniform(randomGenerator, HEIGHT/3);
            rg.makeRoom(world, roomLocation, roomWidth, roomHeight);
        }

        for (Room r : rg.roomList) {  //add doors to all the rooms that exist
            r.makeDoors(world);
        }

        ter = new TERenderer();

        ter.initialize(WIDTH, HEIGHT);

        //Draw the world to the screen
        ter.renderFrame(world);
    }

}
