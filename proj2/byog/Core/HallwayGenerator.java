package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.*;


/** A class to handle hallway generation.
 *  Assumes that rooms are already created and randomly dispersed in world. Will look for
 *  "doorways" (unlocked doors) and create hallways between them.
 */
public class HallwayGenerator {
    private Set<String> DirectionsStraight;
    private Set<String> DirectionsCorner;
    private Random randomGenerator;

    /**
     *  Currently no need to have a HG object except for calling methods
     */
    public HallwayGenerator(Random random) {
        randomGenerator = random;

        DirectionsStraight = new HashSet<>();
        DirectionsCorner = new HashSet<>();
        DirectionsStraight.add("left");
        DirectionsStraight.add("right");
        DirectionsStraight.add("up");
        DirectionsStraight.add("down");
        DirectionsCorner.add("leftUp");
        DirectionsCorner.add("rightUp");
        DirectionsCorner.add("leftDown");
        DirectionsCorner.add("rightDown");
    }

    /**
     * @param allRooms = List of all rooms in world. Try to connect all of them with a straight
     * @param world
     */
    public void connectRoomsStraight(ArrayList<Room> allRooms, List<Position> allRoomCorners, TETile[][] world) {
        for (int passes = 0; passes < 10; passes += 1) { // Tries to connect unconnected rooms 3 times
            for (Room room : allRooms) {
                if (room.connections < 3) {
                    singleRoomConnectMaybe(room, allRoomCorners, world);
                }
            }
        }
    }


    private void singleRoomConnectMaybe(Room room, List<Position> allRoomCorners, TETile[][] world) {
        List<Position> perimeter = room.getPerimeterList(); // an ArrayList
        String direction;

        for (Position pSpot : perimeter) {
            direction = whereIsOutside(pSpot, world);
            int distance = canConnect(pSpot, direction, allRoomCorners, world); // negative is cannot connect

            if (distance > 0) {
                buildHallway(pSpot, distance + 1, direction, world); // might need to make this
                room.connections += 1; // Turning this off might help connect more rooms
                break;
            }
        }
    }

    /** Determines what direction the outside of the room is.
     * @param perimeterSpot = a Position on the perimeter. Should exclude
     * @param world
     * @return
     */
    private String whereIsOutside(Position perimeterSpot, TETile[][] world) {
        String outside = "none"; // used if perimeterSpot is on edge of world
        int pX = perimeterSpot.getX();
        int pY = perimeterSpot.getY();

        // To the left
        if (world[pX - 1][pY] == Tileset.NOTHING) {
            outside = "left";
        }
        // To the right
        if (world[pX + 1][pY] == Tileset.NOTHING) {
            outside = "right";
        }
        // To the up
        if (world[pX][pY + 1] == Tileset.NOTHING) {
            outside = "up";
        }
        // To the down
        if (world[pX][pY - 1] == Tileset.NOTHING) {
            outside = "down";
        }

        return outside;
    }


    /** Checks whether a wall to connect to exists in desired direction.
     *  Returns an integer that is -1 if it cannot connect and, if it can, will be the distance to the next room.
     */
    private int canConnect(Position start, String direction, List<Position> allRoomCorners, TETile[][] world) {
        int increment;
        Position checkIfCorner;

        // Checking rightwards of start
        if (direction == "right") {
            for (increment = start.getX() + 1; increment < world.length; increment += 1) { //start.getX() + 1
                if (world[increment][start.getY()] == Tileset.WALL) {
                    checkIfCorner = new Position(increment, start.getY());
                    for (Position corner : allRoomCorners) {
                        if (checkIfCorner.equals(corner)) {
                            return -1;
                        }
                    }
                    return increment - start.getX(); // Only true if can confirm that the wall I hit is not a corner
                    // break after I hit a wall no matter what, even if it's a corner
                }
            }
        }
        // Checking leftwards of start
        if (direction == "left") {
            for (increment = start.getX() - 1; increment > 0; increment -= 1) { // start.getX() - 1
                if (world[increment][start.getY()] == Tileset.WALL) {
                    checkIfCorner = new Position(increment, start.getY());
                    for (Position corner : allRoomCorners) {
                        if (checkIfCorner.equals(corner)) {
                            return -1;
                        }
                    }
                    return start.getX() - increment; // Only true if can confirm that the wall I hit is not a corner
                  // break after I hit a wall no matter what, even if it's a corner
                }
            }
        }
        // Checking upwards of start
        if (direction == "up") {
            for (increment = start.getY() + 1; increment < world[0].length; increment += 1) { //start.getY() + 1
                if (world[start.getX()][increment] == Tileset.WALL) {
                    checkIfCorner = new Position(start.getX(), increment);
                    for (Position corner : allRoomCorners) {
                        if (checkIfCorner.equals(corner)) {
                            return -1;
                        }
                    }
                    return increment - start.getY();
                }
            }
        }
        // Checking downwards of start
        if (direction == "down") {
            for (increment = start.getY() - 1; increment > 0; increment -= 1) { // start.getY() - 1
                if (world[start.getX()][increment] == Tileset.WALL) {
                    checkIfCorner = new Position(start.getX(), increment);
                    for (Position corner : allRoomCorners) {
                        if (checkIfCorner.equals(corner)) {
                            return -1;
                        }
                    }
                    return start.getY() - increment;
                }
            }
        }
        return -1; // No rooms or hallways to connect to
    }




    /** The publicly-used method for making a hallway. Decides what kind of hallway to make
     *  depending on the parameters.
     * @param start = Position where building starts
     * @param distance = how long to make hallway. Assume 0 for corners
     * @param direction = tells where hallway is going. Can be:
     *                    "left", "right" (horizontal)
     *                    "up", "down" (vertical)
     *                    "leftUp", "rightUp", "rightDown", "leftDown" (corner)
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
            horizontalHallway(distance, (start.getX() - distance + 1), start.getY(), world);
        }
        if (direction == "up") {
            verticalHallway(distance, start.getX(), start.getY(), world);
        }
        if (direction == "down") {
            verticalHallway(distance, start.getX(), (start.getY() - distance + 1), world);
        }
        if (DirectionsCorner.contains(direction)) {
            cornerHallway(direction, start.getX(), start.getY(), world);
        }

        // For debugging
//        System.out.println("I am building a: " + direction + " hallway for: " + distance +
//        " distance starting from position: (" + start.getX() + ", " + start.getY() + ")");

//        return new WhereToNext(direction, start, distance, world); // Old approach, used
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
     *  @param direction is either leftUp, rightUp, rightDown, leftDown
     *  @param hingeX is the hoirzontal position of floor piece where hallway changes directions
     *  @param hingeY is the vertical position of floor piece where hallway changes directions
     */
    private void cornerHallway(String direction, int hingeX, int hingeY, TETile[][] world) {
        world[hingeX][hingeY] = Tileset.FLOOR;

        if (direction == "leftUp") { // <^
            world[hingeX][hingeY - 1] = Tileset.WALL;
            world[hingeX + 1][hingeY - 1] = Tileset.WALL;
            world[hingeX + 1][hingeY] = Tileset.WALL;
        }
        if (direction == "rightUp") { // ^>
            world[hingeX][hingeY - 1] = Tileset.WALL;
            world[hingeX - 1][hingeY - 1] = Tileset.WALL;
            world[hingeX - 1][hingeY] = Tileset.WALL;
        }
        if (direction == "rightDown") { // v>
            world[hingeX][hingeY + 1] = Tileset.WALL;
            world[hingeX - 1][hingeY + 1] = Tileset.WALL;
            world[hingeX - 1][hingeY] = Tileset.WALL;
        }
        if (direction == "leftDown") { // <v
            world[hingeX][hingeY + 1] = Tileset.WALL;
            world[hingeX + 1][hingeY + 1] = Tileset.WALL;
            world[hingeX + 1][hingeY] = Tileset.WALL;
        }
    }

    /** Creates a dead-end, either vertically or horizontally.
     *  @param direction is either left, right, up, down
     */
    public void deadEnd(Position start, String direction, TETile[][] world) {
        if (!DirectionsStraight.contains(direction)) {
            throw new IllegalArgumentException("Cannot place a dead end here. " + direction +
                    " is not a valid direction to feed into a dead end.");
        }
        int horizPos = start.getX();
        int vertPos = start.getY();

        if (direction == "left" || direction == "right") {
            for (int y = 0; y < 3; y += 1) {
                world[horizPos][vertPos - 1 + y] = Tileset.WALL;
            }
        } else {
            for (int x = 0; x < 3; x += 1) {
                world[horizPos - 1 + x][vertPos] = Tileset.WALL;
            }
        }
    }






    //////////////////DEPRECATED AND TRASH CONTENT///////////////////////
    // Uncomment this code if needed again.



//    /** Creates a hallway to connect two unlocked doors. Calls horizontal, vertical,
//     *  and corner constructors as necessary.
//     */
//    public void connect(Position one, Position two, TETile[][] world) {
//        /**
//         *  REMEMBER: corner directions are: leftUp, rightUp, leftDown, rightDown
//         *  What if I made HallwayGenerator recursive?
//         */
//
//        WhereToStart spot = new WhereToStart(one, two);
//
//    }

    // USED TO BE IN WORLDGENERATOR. UNNEEDED AT THE MOMENT
//    /** Creates a List of all unlocked doors in the world (signifying places where rooms need
//     *  to be connected via hallways).
//     */
//    private List<Position> unlockedFinder(TETile[][] world) {
//        List<Position> allUnlocked = new ArrayList<>(20);
//        int worldW = world.length; // Dynamic size of world
//        int worldH = world[0].length;
//
//        for (int xPos = 0; xPos < worldW; xPos += 1) {
//            for (int yPos = 0; yPos < worldH; yPos += 1) {
//                if (world[xPos][yPos].equals(Tileset.UNLOCKED_DOOR)) {
//                    Position p = new Position(xPos, yPos);
//                    allUnlocked.add(p);
//                }
//            }
//        }
//        return allUnlocked;
//    }




}
