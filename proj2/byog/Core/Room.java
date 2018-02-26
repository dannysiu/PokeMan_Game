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


    private Position bottomLeft; //these room corner positions will be used to ensure rooms don't overlap
    private Position bottomRight;
    private Position topLeft;
    private Position topRight;


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

        bottomLeft = p;  //p equals bottom left corner; use as p in the rest of code for brevity
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

        // draw the PERIMETER of the room with wall tiles
        // the room is filled with floor tiles, so will only place walls where not equal floor
        // add positions to perimeter list for future door creation
        // **will need to add doors later
        for (int x = p.getX(); x < p.getX() + w; x += 1) {
            for (int y = p.getY(); y < p.getY() + h; y += 1) {
                if (world[x][y] != Tileset.FLOOR) {
                    world[x][y] = Tileset.WALL;
                    Position perim  = new Position(x, y);
                    perimeterList.add(perim);
                }
            }
        }

        bottomRight = new Position(bottomLeft.getX() + width, bottomLeft.getY());  //documenting the corner positions
        topLeft = new Position(bottomLeft.getX(), bottomLeft.getY() + height);
        topRight = new Position(bottomLeft.getX() + width, bottomLeft.getY() + height);


        cornerList.add(bottomLeft); //add the corner positions to the cornerList
        cornerList.add(bottomRight);
        cornerList.add(topLeft);
        cornerList.add(topRight);
    }


    /** method to choose doors from room perimeter and store their positions */
    public void makeDoors(TETile[][] world) {
        Random randomGenerator = new Random();
        int numDoors = RandomUtils.uniform(randomGenerator, 1, 4);  //between 1 to 3 doors allowed

//        //Test
//        System.out.println("Elements in perimeter list are ");
//        for (Position p : perimeterList) {
//            System.out.println(p);
//        }
//
//        System.out.println("number of items in perimeter list = " + perimeterList.size());
//        //Test ends


        for (int i = 0; i < numDoors; i += 1) {
            int randomInt = RandomUtils.uniform(randomGenerator, perimeterList.size());
            Position door = perimeterList.get(randomInt);
            for (Position d : doorList) {  //ensure no overlapping of hallways by keeping 2-tiles distance between doors
                if (d.calcDistance(door) <= 2.0) {
                    world[door.getX()][door.getY()] = Tileset.UNLOCKED_DOOR;
                    doorList.add(door);
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


}
