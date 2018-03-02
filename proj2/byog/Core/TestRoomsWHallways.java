package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;


public class TestRoomsWHallways {

    /** Visual inspection test. Check that rooms and doorways are made correctly.
     *  Simulate making many rooms to save time when checking and check room interactions.
     *  */
    public static void main(String[] args) {
        //Draw the screen
        TERenderer ter;
        int worldWidth = 80;
        int worldHeight = 40;

        TETile[][] world = new TETile[worldWidth][worldHeight];
        for (int x = 0; x < worldWidth; x += 1) {
            for (int y = 0; y < worldHeight; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        //Now for building rooms in world

        Random randomGenerator = new Random();


        RoomGenerator rg = new RoomGenerator();
        //make up to 50 rooms in the world; some will overlap and fail to exist
        for (int i = 0; i < 50; i += 1) {
            int posX = RandomUtils.uniform(randomGenerator, worldWidth);
            int posY = RandomUtils.uniform(randomGenerator, worldHeight);
            Position roomLocation = new Position(posX, posY);
            int roomWidth = RandomUtils.uniform(randomGenerator, 4, worldWidth / 4);
            int roomHeight = RandomUtils.uniform(randomGenerator, 4, worldHeight / 4);
            rg.makeRoom(world, roomLocation, roomWidth, roomHeight);
        }




        HallwayGenerator hg = new HallwayGenerator(randomGenerator);
        hg.connectRoomsStraight(rg.getRoomList(), rg.getCornerBlacklist(), world);



        ter = new TERenderer();

        ter.initialize(worldWidth, worldHeight);

        //Draw the world to the screen
        ter.renderFrame(world);
    }

}
