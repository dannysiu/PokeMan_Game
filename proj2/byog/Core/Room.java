package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;
import byog.Core.RandomUtils;
import java.util.Random;

public class Room {

    public int width;  //dimensions of room
    public int height;

    public ArrayList<Position> doorList;
    public ArrayList<Position> perimeterList;  //stores positions for possible doorways
    public ArrayList<Position> cornerList;  //cannot make doors where there are corners


    private Position topLeft;  //these room corner positions will be used to ensure rooms don't overlap
    private Position topRight;
    private Position bottomLeft;
    private Position bottomRight;


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
     * @param p     position of upper left corner of room
     * @param w     width of room
     * @param h     height of room
     * @param -use  for any extra parameters. Ex.add doors later -> will need to change wall tiles
     * @source inspiration for comment format + some ideas from josh hug
     */

    public Room(TETile[][] world, Position p, int w, int h) {

        topLeft = p;
        width = w;
        height = h;

        doorList = new ArrayList<Position>(2);
        perimeterList = new ArrayList<Position>(10);
        cornerList = new ArrayList<Position>(4);



        //draw the INSIDE of the room with floor tiles
        for (int x_incr = 1; x_incr < width - 1; x_incr += 1) {
            for (int y_incr = 1; y_incr < height - 1; y_incr += 1) {
                int new_x = topLeft.getX() + x_incr;
                int new_y = topLeft.getY() + y_incr;
                world[new_x][new_y] = Tileset.FLOOR;
            }
        }

        // draw the PERIMETER of the room with wall tiles
        // the room is filled with floor tiles, so will only place walls where not equal floor
        // add positions to perimeter list for future door creation
        // **will need to add doors later
        for (int x_incr = 0; x_incr < width; x_incr += 1) {
            for (int y_incr = 0; y_incr < height; y_incr += 1) {
                int new_x = topLeft.getX() + x_incr;
                int new_y = topLeft.getY() + y_incr;
                if (world[new_x][new_y] != Tileset.FLOOR) {
                    world[new_x][new_y] = Tileset.WALL;
                    Position perim  = new Position(new_x, new_y);
                    perimeterList.add(perim);
                }
            }
        }

        topRight = new Position(topLeft.getX() + width, topLeft.getY());  //documenting the corner positions
        bottomLeft = new Position(topLeft.getX(), topLeft.getY() + height);
        bottomRight = new Position(topLeft.getX() + width, topLeft.getY());

        cornerList.add(topLeft); //add the corner positions to the cornerList
        cornerList.add(topRight);
        cornerList.add(bottomLeft);
        cornerList.add(bottomRight);
    }


    /** method to choose doors from room perimeter and store their positions */
    public void makeDoors(TETile[][] world) {
        Random randomGenerator = new Random();
        int numDoors = RandomUtils.uniform(randomGenerator, 1, 4);  //between 1 to 3 doors allowed

        for (int i = 0; i < numDoors; i += 1) {
            int randomInt = RandomUtils.uniform(randomGenerator, perimeterList.size());
            Position door = perimeterList.get(randomInt);
            if (!doorList.contains(door) ) {
                world[door.getX()][door.getY()] = Tileset.UNLOCKED_DOOR;
                doorList.add(door);
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

}
