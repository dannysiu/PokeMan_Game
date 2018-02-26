package byog.Core;

import byog.Core.HallwayGenerator;
import static org.junit.Assert.*;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

public class TestRoomGenerator {
    /** Visual inspection test. Change width and height declared near beginning to affect world. */
    public static void main (String[] args) {
        //Draw the screen
        TERenderer ter;
        int width = 80;
        int height = 50;

        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        //Now for building rooms in world
        Position roomlocation = new Position(20, 20);

        RoomGenerator rg = new RoomGenerator();
        Room room1 = rg.makeRoom(world, roomlocation, 20, 20);
        room1.makeDoors(world);

        ter = new TERenderer();

        ter.initialize(width, height);

        //Draw the world to the screen
        ter.renderFrame(world);
    }

}
