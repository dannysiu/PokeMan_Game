package byog.Core;

import byog.Core.WorldGenerator;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import javafx.geometry.Pos;

import java.security.InvalidParameterException;

/** A class to handle hallway generation.
 *  Assumes that rooms are already created and randomly dispersed in world. Will look for
 *  "doorways" (unlocked doors) and create hallways between them.
 *  TODO: Method to link hallways together.
 */
public class HallwayGenerator {



    /** Creates a hallway to connect two unlocked doors. Calls horizontal, vertical,
     *  and corner constructors as necessary.
     *  Currently no need to have a HG object except for calling methods
     */
    public HallwayGenerator() { }


    /** The publicly-used method for making a hallway. Decides what kind of hallway to make
     *  depending on the parameters.
     * @param start = Position where building starts
     * @param distance = how long to make hallway. Assume 0 for corners
     * @param direction = tells where hallway is going. Can be:
     *                    "left", "right" (horizontal)
     *                    "up", "down" (vertical)
     *                    "leftTop", "rightTop", "rightBottom", "leftBottom" (corner)
     * @param world = world that hallways are being put in
     */
    public void buildHallway(Position start, int distance, String direction, TETile[][] world) {



    }


    // MOVED THIS METHOD TO POSITION CLASS
//    /** Checks whether there are any WALLS or FLOORS where a hallway is planned.
//     *  Assume that @params one and two are either horizontally aligned or vertically aligned.
//     *  Assume that the left-most or bottom-most Position is @param one.
//     */
//    private boolean unobstructed(Position one, Position two, TETile[][] world) {
//
//        if ((two.getX() - one.getX()) != 0 || (two.getY() - one.getY()) != 0) {
//            throw new IllegalArgumentException("Positions one and two are not horizontally" +
//                    "aligned or vertically aligned.");
//        }
//
//        for (int x = one.getX(); x <= two.getX(); x += 1) {
//            for (int y = one.getY(); y <= two.getY(); y += 1) {
//                if (world[x][y].equals(Tileset.WALL) || world[x][y].equals(Tileset.FLOOR)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }



    /////////////ABSTRACTION BARRIER: NO POSITION OBJECTS BENEATH THIS POINT///////////



    /** Creates a horizontal hallway.
     */
    private void horizontalHallway(int length, int horizPos, int vertPos, TETile[][] world) {

        for (int i = horizPos; i < (horizPos + length); i += 1) {
            world[i][vertPos] = Tileset.FLOOR;
            world[i][vertPos - 1] = Tileset.WALL;
            world[i][vertPos + 1] = Tileset.WALL;
        }
    }


    /** Creates a vertical hallway.
     */
    private void verticalHallway(int height, int horizPos, int vertPos, TETile[][] world) {

        for (int i = vertPos; i < (vertPos + height); i += 1) {
            world[horizPos][i] = Tileset.FLOOR;
            world[horizPos - 1][i] = Tileset.WALL;
            world[horizPos + 1][i] = Tileset.WALL;
        }
    }

    /** Creates an L corner connecting hallways.
     *  @param direction is either leftTop, rightTop, rightBottom, leftBottom
     *  @param hingeX is the hoirzontal position of floor piece where hallway changes directions
     *  @param hingeY is the vertical position of floor piece where hallway changes directions
     */
    private void cornerHallway(String direction, int hingeX, int hingeY, TETile[][] world) {
        world[hingeX][hingeY] = Tileset.FLOOR;

        if (direction == "leftTop") { // <^
            world[hingeX][hingeY - 1] = Tileset.WALL;
            world[hingeX + 1][hingeY - 1] = Tileset.WALL;
            world[hingeX + 1][hingeY] = Tileset.WALL;
        }
        if (direction == "rightTop") { // ^>
            world[hingeX][hingeY - 1] = Tileset.WALL;
            world[hingeX - 1][hingeY - 1] = Tileset.WALL;
            world[hingeX - 1][hingeY] = Tileset.WALL;
        }
        if (direction == "rightBottom") { // v>
            world[hingeX][hingeY + 1] = Tileset.WALL;
            world[hingeX - 1][hingeY + 1] = Tileset.WALL;
            world[hingeX - 1][hingeY] = Tileset.WALL;
        }
        if (direction == "leftBottom") { // <v
            world[hingeX][hingeY + 1] = Tileset.WALL;
            world[hingeX + 1][hingeY + 1] = Tileset.WALL;
            world[hingeX + 1][hingeY] = Tileset.WALL;
        }
    }







    //////////////////DEPRECATED AND TRASH CONTENT///////////////////////
    // Uncomment this code if needed again.


//    /** Helper class object to determine what should be starting point of two positions for
//     *  hallway construction.
//     *  Optimal starting Position is by smallest x, then smallest y (left-most, bottom-most)
//     */
//    private class WhereToStart {
//        Position start;
//        Position end;
//        String path; // Options: "up", "down", "left", "right"
//
//        private WhereToStart(Position one, Position two) {
//            if (one.getX() < two.getX()) { // Favor Position with lower x
//                start = one;
//                end = two;
//            } else if (one.getX() > two.getX()) {
//                start = two;
//                end = one;
//            } else if (one.getX() == two.getX()) { // Same x, favor Position with lower y
//                if (one.getY() < two.getY()) {
//                    start = one;
//                    end = two;
//                } else {
//                    start = two;
//                    end = one;
//                }
//            }
//        }
//
//        /** Return the direction that hallway should start being built */
//        private String direction() {
//            if (start.getX() < end.getX()) { // Begin by moving to right
//                //never finished
//            }
//            return "none";
//        }
//    }
//
//    /** Creates a hallway to connect two unlocked doors. Calls horizontal, vertical,
//     *  and corner constructors as necessary.
//     */
//    public void connect(Position one, Position two, TETile[][] world) {
//        /** TODO: Algorithm to select what hallways needed to connect two positions.
//         *  TODO: Determine what kind of corner hallway to use in a given situation.
//         *  REMEMBER: corner directions are: leftTop, rightTop, leftBottom, leftBottom
//         *  What if I made HallwayGenerator recursive?
//         */
//
//        WhereToStart spot = new WhereToStart(one, two);
//
//    }

}
