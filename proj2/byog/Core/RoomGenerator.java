package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;

public class RoomGenerator {

    TERenderer ter = new TERenderer();  // needed?

    private ArrayList<Room> roomList;
    private static ArrayList<Position> cornerBlacklist;
    //update corner blacklist when making rooms, so we can ensure hallways don't connect at corners
    private static int roomCount;

    /**
     * Object will be used to make new rooms
     */
    public RoomGenerator() {
        roomList = new ArrayList(5);
        cornerBlacklist = new ArrayList(4);
        roomCount = 0;
    }


    // Restriction: 2 rooms can't overlap
    public void makeRoom(TETile[][] world, Position p, int w, int h) {
        /**check that for the whole border of the potential room, it doesn't overlap with
         * another room aka, tile in the world !equal wall or floor !equals tile.nothing
         */

        int worldWidth = world.length;
        int worldHeight = world[0].length;


        boolean buildCuzYouCan = true;
        for (int x = p.getX(); x < p.getX() + w; x += 1) {
            for (int y = p.getY(); y < p.getY() + h; y += 1) {
                if ((x >= worldWidth) || (y >= worldHeight)) {
                    buildCuzYouCan = false;
                    return;
                } else if (world[x][y] != Tileset.NOTHING) {
                    buildCuzYouCan = false;
                    return;
                }
            }
        }

        if (buildCuzYouCan) {
            Room newRoom = new Room(world, p, w, h);
            roomList.add(newRoom);
            cornerBlacklist.addAll(newRoom.getCornerList());
        }
    }


    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public static ArrayList<Position> getCornerBlacklist() {
        return cornerBlacklist;
    }
}
