package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;

public class RoomGenerator {

    TERenderer ter = new TERenderer();  // needed?

    public ArrayList roomList;

    /** Object will be used to make new rooms */
    public RoomGenerator() {
        roomList = new ArrayList(5);
    }


    public Room makeRoom(TETile[][] world, Position p, int w, int h) {
//        if (p.getX())
        Room newRoom = new Room(world, p, w, h);
        roomList.add(newRoom);
        return newRoom;





        }

}
