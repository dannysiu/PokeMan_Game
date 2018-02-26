package byog.Core;

import byog.Core.WorldGenerator;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import javafx.geometry.Pos;
import javafx.scene.control.skin.TextInputControlSkin;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

/** A class to handle hallway generation.
 *  Assumes that rooms are already created and randomly dispersed in world. Will look for
 *  "doorways" (unlocked doors) and create hallways between them.
 *  TODO: Method to link hallways together.
 */
public class HallwayGenerator {
    private Set<String> DirectionsStraight;
    private Set<String> DirectionsCorner;

    /** Creates a hallway to connect two unlocked doors. Calls horizontal, vertical,
     *  and corner constructors as necessary.
     *  Currently no need to have a HG object except for calling methods
     */
    public HallwayGenerator() {
        DirectionsStraight = new HashSet<>();
        DirectionsCorner = new HashSet<>();
        DirectionsStraight.add("left");
        DirectionsStraight.add("right");
        DirectionsStraight.add("up");
        DirectionsStraight.add("down");
        DirectionsCorner.add("leftTop");
        DirectionsCorner.add("rightTop");
        DirectionsCorner.add("leftBottom");
        DirectionsCorner.add("rightBottom");
    }


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
        if ((! DirectionsCorner.contains(direction)) && (! DirectionsStraight.contains(direction))) {
            throw new IllegalArgumentException(direction + " is not a valid direction");
        }

        if (direction == "right") {
            horizontalHallway(distance, start.getX(), start.getY(), world);
        }
        if (direction == "left") {
            horizontalHallway(distance, (start.getX() - distance), start.getY(), world);
        }
        if (direction == "up") {
            verticalHallway(distance, start.getX(), start.getY(), world);
        }
        if (direction == "down") {
            verticalHallway(distance, start.getX(), (start.getY() - distance), world);
        }
        if (direction == "leftTop" || direction == "rightTop" || direction == "leftBottom" || direction == "rightBottom") {
            cornerHallway(direction, start.getX(), start.getY(), world);
        }
//        if (direction == "rightTop") {
//            cornerHallway(direction, start.getX(), start.getY(), world);
//        }
//        if (direction == "leftBottom") {
//            cornerHallway(direction, start.getX(), start.getY(), world);
//        }
//        if (direction == "rightBottom") {
//            cornerHallway(direction, start.getX(), start.getY(), world);
//        }
    }

    /** An object to be returned by buildHallway method above. Tells WorldGeneration algorithm where
     *  to start the next build and in what direction the current hallway is pointed.
     */
    public class WhereToNext {
        String newDirection;
        Position newPosition;
        Boolean noRoom; // True if a room can't go here (ie. just turned a corner)

        private WhereToNext(String direction, Position start, int distance, TETile[][] world) {
            // TODO: use unobstructedHallway method to figure out for corners the next direction
            // TODO: make sure newPosition is 1 away from wherever buildHallway finished

            if (DirectionsStraight.contains(direction)) {
                newDirection = direction;
                noRoom = false;
            }

        }

    }



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
//        /**
//         *  REMEMBER: corner directions are: leftTop, rightTop, leftBottom, leftBottom
//         *  What if I made HallwayGenerator recursive?
//         */
//
//        WhereToStart spot = new WhereToStart(one, two);
//
//    }

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

}
