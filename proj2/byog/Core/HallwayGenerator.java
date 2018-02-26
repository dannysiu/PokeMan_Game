package byog.Core;

import byog.Core.WorldGenerator;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import javafx.geometry.Pos;

/** A class to handle hallway generation.
 *  Assumes that rooms are already created and randomly dispersed in world. Will look for
 *  "doorways" (unlocked doors) and create hallways between them.
 *  TODO: Method to create horizontal hallways.
 *  TODO: Method to create vertical hallways.
 *  TODO: Method to create corners.
 *  TODO: Method to link hallways together.
 */
public class HallwayGenerator {

    /** Creates a hallway to connect two unlocked doors. Calls horizontal, vertical,
     *  and corner constructors as necessary.
     */
    public HallwayGenerator(Position one, Position two, TETile[][] world) {
        /** TODO: Algorithm to select what hallways needed to connect two positions.
         *  TODO: Determine what kind of corner hallway to use in a given situation.
         *  REMEMBER: corner directions are: leftTop, rightTop, leftBottom, leftBottom
         *  What if I made HallwayGenerator recursive?
         */









//        // FOR TESTING PURPOSES
//        int oneX = one.getX();
//        int twoX = two.getX();
//        int oneY = one.getY();
//        int twoY = two.getY();
//        horizontalHallway((twoX - oneX), oneX, oneY, world);
//        cornerHallway("leftTop", two, world);
//        verticalHallway(10, twoX, twoY + 1, world);
//        // END OF TESTING COMPONENTS
    }

    /** Helper class object to determine what should be starting point of two positions for
     *  hallway construction.
     *  Optimal starting Position is by smallest x, then smallest y (left-most, bottom-most)
     */
    private class WhereToStart {
        Position start;
        Position end;

        private WhereToStart(Position one, Position two) {
            if (one.getX() < two.getX()) {
                start = one;
                end = two;
            } else if (one.getX() > two.getX()) {
                start = two;
                end = one;
            } else if (one.getX() == two.getX()) {
                if (one.getY() < two.getY()) {
                    start = one;
                    end = two;
                } else {
                    start = two;
                    end = one;
                }
            }
        }



    }

    /** Helper method for determining which Position to start from */
    private Position farthestLeft(Position one, Position two) {
        if (one.getX() <= two.getX()) {
            return one;
        } else {
            return two;
        }
    }



    /** Checks whether there are any WALLS or FLOORS where a hallway is planned.
     *  Assume that @params one and two are either horizontally aligned or vertically aligned.
     *  Assume that the left-most or bottom-most Position is @param one.
     */
    private boolean unobstructed(Position one, Position two, TETile[][] world) {

        for (int x = one.getX(); x <= two.getX(); x += 1) {
            for (int y = one.getY(); y <= two.getY(); y += 1) {
                if (world[x][y].equals(Tileset.WALL)) {
                    return false;
                }
            }
        }
        return true;
    }



    /////////////ABSTRACTION BARRIER: NO POSITION OBJECTS BENEATH THIS POINT///////////



    /** Creates a horizonatal hallway.
     *  TODO: change so it can also build hallways right to left.
     */
    private void horizontalHallway(int length, int horizPos, int vertPos, TETile[][] world) {

        for (int i = horizPos; i < (horizPos + length); i += 1) {
            world[i][vertPos] = Tileset.FLOOR;
            world[i][vertPos - 1] = Tileset.WALL;
            world[i][vertPos + 1] = Tileset.WALL;
        }
    }


    /** Creates a vertical hallway.
     *  TODO: change so it can also build hallways up-to-down.
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

}
