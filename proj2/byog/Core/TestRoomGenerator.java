package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class TestRoomGenerator {
    /** Visual inspection test. Check that rooms and doorways are made correctly.
     *  Simulate making many rooms to save time when checking and check room interactions.
     *  */
    public static void main(String[] args) {
        //Draw the screen
        TERenderer ter;
        int worldWidth = 50;
        int worldHeight = 30;

        TETile[][] world = new TETile[worldWidth][worldHeight];
        for (int x = 0; x < worldWidth; x += 1) {
            for (int y = 0; y < worldHeight; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        //Now for building rooms in world

        Random randomGenerator = new Random();


        RoomGenerator rg = new RoomGenerator(randomGenerator);
        //make up to 50 rooms in the world; some will overlap and fail to exist
        for (int i = 0; i < 50; i += 1) {
            int posX = RandomUtils.uniform(randomGenerator, worldWidth);
            int posY = RandomUtils.uniform(randomGenerator, worldHeight);
            Position roomLocation = new Position(posX, posY);
            int roomworldWidth = RandomUtils.uniform(randomGenerator, 3, worldWidth / 3);
            int roomworldHeight = RandomUtils.uniform(randomGenerator, 3, worldHeight / 3);
            rg.makeRoom(world, roomLocation, roomworldWidth, roomworldHeight);
        }

//        for (Room r : rg.getRoomList()) {  //add doors to all the rooms that exist
//            r.makeDoors(world, randomGenerator);
//
//        }

        ter = new TERenderer();

        ter.initialize(worldWidth, worldHeight);

        //Draw the world to the screen
        ter.renderFrame(world);
    }

}
