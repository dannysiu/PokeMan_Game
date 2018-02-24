package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class RoomGenerator {

    TERenderer ter = new TERenderer();  // needed?

    public int width;  //dimensions of room
    public int height;


    private Position topLeft;  //these room corner positions will be used to ensure rooms don't overlap
    private Position topRight;
    private Position bottomLeft;
    private Position bottomRight;


    /** Criteria to consider when making a room:
     * Room is smaller than world
     * Room does not overlap other rooms (can ensure in world generator, or store list of room positions here)
     * Where do we start to make the room (corner or center)
     *
     *
     * Add a room within the world
     * @source inspiration for comment format + some ideas from josh hug
     * @param world the world to draw in
     * @param p position of upper left corner of room
     * @param w width of room
     * @param h height of room
     * @param -use for any extra parameters. Ex.add doors later -> will need to change wall tiles
     *
     * */

    public Room makeRoom(TETile[][] world, Position p, int w, int h) {
        //set room variables
        topLeft = p;
        width = w;
        height = h;


        //draw the PERIMETER of the room with wall tiles
        //the room is filled with wall tiles, then the room's inside will be replaced by floor tiles
        // **will need to add doors later
        for (int x_incr = 0; x_incr < width; x_incr += 1) {
            for (int y_incr = 0; y_incr < height; y_incr += 1) {
                int new_x = topLeft.getX() + x_incr;
                int new_y = topLeft.getY() + y_incr;
                world[new_x][new_y] = Tileset.WALL;
            }
        }

        //draw the INSIDE of the room with floor tiles
        //will replace most wall tiles with floor tiles
        for (int x_incr = 1; x_incr < width - 1; x_incr += 1) {
            for (int y_incr = 1; y_incr < height - 1; y_incr += 1) {
                int new_x = topLeft.getX() + x_incr;
                int new_y = topLeft.getY() + y_incr;
                world[new_x][new_y] = Tileset.FLOOR;
            }
        }

        topRight = new Position(topLeft.getX() + width, topLeft.getY());  //documenting the corner positions
        bottomLeft = new Position(topLeft.getX(), topLeft.getY() + height);
        bottomRight = new Position(topLeft.getX() + width, topLeft.getY());

    }

    private class Room {

        public Room(int w, int h) {
            width = w;
            height = h;)

        }

    }




}
