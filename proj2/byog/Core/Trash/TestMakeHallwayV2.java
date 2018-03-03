package byog.Core.Trash;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;
import byog.Core.RoomGenerator;
import byog.Core.Room;
import byog.Core.RandomUtils;
import byog.Core.Position;


/** Test for making a turning hallway between rooms */
public class TestMakeHallwayV2 {

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

        //Now for building 2 rooms in world

        Random randomGenerator = new Random();
        RoomGenerator rg = new RoomGenerator(randomGenerator);
        while (rg.getRoomList().size() < 2) {
            int posX = RandomUtils.uniform(randomGenerator, worldWidth);
            int posY = RandomUtils.uniform(randomGenerator, worldHeight);
            Position roomLocation = new Position(posX, posY);
            int roomworldWidth = RandomUtils.uniform(randomGenerator, 3, worldWidth / 3);
            int roomworldHeight = RandomUtils.uniform(randomGenerator, 3, worldHeight / 3);
            rg.makeRoom(world, roomLocation, roomworldWidth, roomworldHeight);
        }


        // build a turning hallway to connect the rooms
        MakeHallwayV2 mh = new MakeHallwayV2();
        Room r1 = rg.getRoomList().get(0);
        Room r2 = rg.getRoomList().get(1);
//        mh.buildHallway(world, r1, r2,);


        ter = new TERenderer();

        ter.initialize(worldWidth, worldHeight);

        //Draw the world to the screen
        ter.renderFrame(world);
    }

}
