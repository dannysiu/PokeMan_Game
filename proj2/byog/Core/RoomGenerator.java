package byog.Core;

import byog.TileEngine.TERenderer;

import byog.Core.Position;

public class RoomGenerator {

    TERenderer ter = new TERenderer();  //allows tiles

    public int width;  //dimensions of room
    public int height;


    private Position topLeft;
    private Position topRight;
    private Position bottomLeft;
    private Position BottomRight;


    /** Criteria to consider when making a room:
     * Room is smaller than world
     * Room does not overlap other rooms (can ensure in world generator, or store list of room positions here)
     * Where do we start to make the room (corner or center)
     *
     *
     * Add a room within the world
     * @param p
     * */

    public Room makeRoom(Position p, int w, int h) {


    }

    private class Room {

        public Room(int w, int h) {
            width = w;
            height = h;)

        }

    }




}
