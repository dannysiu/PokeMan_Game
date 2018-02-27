package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.Random;

public class Room {

    public int width;  //dimensions of room
    public int height;

    public ArrayList<Position> doorList;
    public ArrayList<Position> perimeterList;  //stores positions for possible doorways
    public ArrayList<Position> cornerList;  //cannot make doors where there are corners


    private Position bottomLeft; //these room corner positions will be used to ensure rooms don't overlap
    private Position bottomRight;
    private Position topLeft;
    private Position topRight;

    public boolean connected = true;

    /**
     * Criteria to consider when making a room:
     * Room is smaller than world
     * Room does not overlap other rooms (can ensure in world generator, or store list of room positions here)
     * Where do we start to make the room (corner or center)
     */

    /**
     * Add a room within the world
     *
     * @param world the world to draw in
     * @param p     position of bottom left corner of room
     * @param w     width of room
     * @param h     height of room
     * @param -use  for any extra parameters. Ex.add doors later -> will need to change wall tiles
     * @source inspiration for comment format + some ideas from josh hug
     */

    public Room(TETile[][] world, Position p, int w, int h) {
        width = w;
        height = h;

        doorList = new ArrayList<Position>(2);
        perimeterList = new ArrayList<Position>(10);
        cornerList = new ArrayList<Position>(4);


        //draw the INSIDE of the room with floor tiles
        for (int x = p.getX() + 1; x < p.getX() + width - 1; x += 1) {
            for (int y = p.getY() + 1; y < p.getY() + height - 1; y += 1) {
                world[x][y] = Tileset.FLOOR;
            }
        }

        //documenting the corner positions and adding to cornerList
        bottomLeft = p;
        bottomRight = new Position(bottomLeft.getX() + width - 1, bottomLeft.getY());
        topLeft = new Position(bottomLeft.getX(), bottomLeft.getY() + height - 1);
        topRight = new Position(bottomLeft.getX() + width - 1, bottomLeft.getY() + height - 1);
        cornerList.add(bottomLeft);
        cornerList.add(bottomRight);
        cornerList.add(topLeft);
        cornerList.add(topRight);

        // draw the PERIMETER of the room with wall tiles
        // the room is filled with floor tiles, so will only place walls where not equal floor
        // add positions to perimeter list for future door creation, EXCLUDING CORNERS
        // **will need to add doors later
        for (int x = p.getX(); x < p.getX() + w; x += 1) {
            for (int y = p.getY(); y < p.getY() + h; y += 1) {
                if (world[x][y] != Tileset.FLOOR) {
                    world[x][y] = Tileset.WALL;
                    Position perim = new Position(x, y);

                    boolean addToPerimList = true;  //add to perimLxist ONLY if not a corner

                    for (Position corner : cornerList) {
                        if (corner.equals(perim)) {
                            addToPerimList = false;
                        }
                    }

                    if (addToPerimList) {
                        perimeterList.add(perim);
                    }
                }
            }
        }
    }






    /** These are methods for getting lists of positions in order to make hallway connections
     * */
    public ArrayList<Position> getDoorList() { //hallways connect door locations
        return doorList;
    }

    public ArrayList<Position> getCornerList() { //hallways not allowed at corner locations
        return cornerList;
    }

    public ArrayList<Position> getPerimeterList() {
        return perimeterList;
    }

    public boolean isConnected() {
        return connected;
    }



//    /** --DEPRECATED METHOD: We no longer make doors after making rooms.
//     *  Now we make doors wherever hallways connect rooms. -- */
//
//    /** method to choose doors from room perimeter and store their positions */
//    public void makeDoors(TETile[][] world, Random random) {
//        int numDoors = RandomUtils.uniform(random, 1, 4);  //between 1 to 3 doors allowed
//
//        for (int i = 0; i < numDoors; i += 1) {
//            int randomInt = RandomUtils.uniform(random, perimeterList.size());
//            Position door = perimeterList.get(randomInt);
//
//            /** Check that doors are not on corners and have 2-tiles distance between other doors,
//             *  so halls don't overlap
//             * */
//            boolean noDoorOverlapOrCorners = true;
//            for (Position d : doorList) {
//                if (d.calcDistance(door) <= 2.0) {
//                    noDoorOverlapOrCorners = false;
//                }
//            }
//
//            /** if doors don't overlap, then add doors */
//            if (noDoorOverlapOrCorners) {
//                world[door.getX()][door.getY()] = Tileset.UNLOCKED_DOOR;
//                doorList.add(door);
//            }
//        }
//    }

}
