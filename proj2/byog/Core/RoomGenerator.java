package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;

public class RoomGenerator {

    TERenderer ter = new TERenderer();  // needed?

    public ArrayList roomList;


    public Room makeRoom(TETile[][] world, Position p, int w, int h) {
        Room newRoom = new Room(world, p, w, h);
        roomList.add(newRoom);
        return newRoom;





        }

}
