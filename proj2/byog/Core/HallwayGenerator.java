package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Set;
import java.util.Random;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


/** A class to handle hallway generation.
 *  Assumes that rooms are already created and randomly dispersed in world. Will look for
 *  "doorways" (unlocked doors) and create hallways between them.
 */
public class HallwayGenerator {
    private Set<String> directionsStraight;
    private Set<String> directionsCorner;
    private Random randomGenerator;

    /**
     *  Currently no need to have a HG object except for calling methods
     */
    public HallwayGenerator(Random random) {
        randomGenerator = random;

        directionsStraight = new HashSet<>();
        directionsCorner = new HashSet<>();
        directionsStraight.add("left");
        directionsStraight.add("right");
        directionsStraight.add("up");
        directionsStraight.add("down");
        directionsCorner.add("leftUp");
        directionsCorner.add("rightUp");
        directionsCorner.add("leftDown");
        directionsCorner.add("rightDown");
    }

    /**
     * @param allRooms = List of all rooms in world. Try to connect all of them straight
     * @param world
     */
    public void connectRoomsStraight(ArrayList<Room> allRooms,
                                     TETile[][] world) {
        for (int passes = 0; passes < 10; passes += 1) { // Passes = attempts to connect
            for (Room room : allRooms) {
                if (room.getConnections() < 3) {
                    singleRoomConnectMaybe(room, world);
                }
            }
        }
        for (Room room : allRooms) {
            if (room.getConnections() == 0) {
                singleRoomConnectMaybe(room, world);
            }

        }
    }


    private void singleRoomConnectMaybe(Room room, TETile[][] world) {
        List<Position> perimeter = room.getPerimeterList(); // an ArrayList
        String direction;


        Position[] perimeterArray = new Position[perimeter.size()];
        int index = 0;
        for (Position spot : perimeter) {
            perimeterArray[index] = spot;
            index += 1;
        } // Shuffle the array in order to make hallway connection more random, more interesting
        byog.Core.RandomUtils.shuffle(randomGenerator, perimeterArray);

        for (Position pSpot : perimeterArray) {
            direction = whereIsOutside(pSpot, world);
            int distance = canConnect(pSpot, direction, world);

            if (distance >= 0) { // If distance is negative, cannot connect
                buildHallway(pSpot, distance, direction, world);
                room.incrementConnections();
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

        if (world[pX + 1][pY] == Tileset.FLOOR) {  // To the left
            outside = "left";
        } else if (world[pX - 1][pY] == Tileset.FLOOR) { // To the right
            outside = "right";
        } else if (world[pX][pY - 1] == Tileset.FLOOR) { // To the up
            outside = "up";
        } else if (world[pX][pY + 1] == Tileset.FLOOR) { // To the down
            outside = "down";
        }

        return outside;
    }


    /** Checks whether a wall to connect to exists in desired direction.
     *  Returns an integer that is -1 if it cannot connect and, if it can,
     *  will be the distance to the next room.
     */
    private int canConnect(Position start, String direction, TETile[][] world) {
        int increment;
        Position checkIfCorner;
        Position checkNeighbors;


        // Checking rightwards of start
        if (direction.equals("right")) {
            for (increment = start.getX() + 1; increment < world.length; increment += 1) {
                if (world[increment][start.getY()] == Tileset.FLOOR) {
                    return increment - start.getX();
                }
            }
        }
        // Checking leftwards of start
        if (direction.equals("left")) {
            for (increment = start.getX() - 1; increment > 0; increment -= 1) { // start.getX() - 1
                if (world[increment][start.getY()] == Tileset.FLOOR) {
                    return start.getX() - increment; // If positive, not a corner
                }
//                System.out.println(increment + ", " + start.getY());
            }
        }
        // Checking upwards of start
        if (direction.equals("up")) {
            for (increment = start.getY() + 1; increment < world[0].length;
                 increment += 1) { //start.getY() + 1
                if (world[start.getX()][increment] == Tileset.FLOOR) {
                    return increment - start.getY();
                }
            }
        }
        // Checking downwards of start
        if (direction.equals("down")) {
            for (increment = start.getY() - 1; increment > 0; increment -= 1) { // start.getY() - 1
                if (world[start.getX()][increment] == Tileset.FLOOR) {
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
        if ((!directionsCorner.contains(direction))
                && (!directionsStraight.contains(direction))) {
            throw new IllegalArgumentException(direction + " is not a valid direction");
        }

        if (direction.equals("right")) {
            horizontalHallway(distance, start.getX(), start.getY(), world);
        }
        if (direction.equals("left")) {
            horizontalHallway(distance, (start.getX() - distance + 1), start.getY(), world);
        }
        if (direction.equals("up")) {
            verticalHallway(distance, start.getX(), start.getY(), world);
        }
        if (direction.equals("down")) {
            verticalHallway(distance, start.getX(), (start.getY() - distance + 1), world);
        }
        if (directionsCorner.contains(direction)) {
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
            if (world[i][vertPos - 1] != Tileset.FLOOR) {
                world[i][vertPos - 1] = Tileset.WALL;
            }
            if (world[i][vertPos + 1] != Tileset.FLOOR) {
                world[i][vertPos + 1] = Tileset.WALL;
            }
        }
    }


    /** Creates a vertical hallway.
     */
    private void verticalHallway(int height, int horizPos, int vertPos, TETile[][] world) {

        for (int i = vertPos; i < (vertPos + height); i += 1) {
            world[horizPos][i] = Tileset.FLOOR;
            if (world[horizPos - 1][i] != Tileset.FLOOR) {
                world[horizPos - 1][i] = Tileset.WALL;
            }
            if (world[horizPos + 1][i] != Tileset.FLOOR) {
                world[horizPos + 1][i] = Tileset.WALL;
            }
        }
    }

    /** Creates an L corner connecting hallways.
     *  @param direction is either leftUp, rightUp, rightDown, leftDown
     *  @param hingeX is the horizontal position of floor piece where hallway changes directions
     *  @param hingeY is the vertical position of floor piece where hallway changes directions
     */
    private void cornerHallway(String direction, int hingeX, int hingeY, TETile[][] world) {
        world[hingeX][hingeY] = Tileset.FLOOR;

        if (direction.equals("leftUp")) { // <^
            world[hingeX][hingeY - 1] = Tileset.WALL;
            world[hingeX + 1][hingeY - 1] = Tileset.WALL;
            world[hingeX + 1][hingeY] = Tileset.WALL;
        }
        if (direction.equals("rightUp")) { // ^>
            world[hingeX][hingeY - 1] = Tileset.WALL;
            world[hingeX - 1][hingeY - 1] = Tileset.WALL;
            world[hingeX - 1][hingeY] = Tileset.WALL;
        }
        if (direction.equals("rightDown")) { // v>
            world[hingeX][hingeY + 1] = Tileset.WALL;
            world[hingeX - 1][hingeY + 1] = Tileset.WALL;
            world[hingeX - 1][hingeY] = Tileset.WALL;
        }
        if (direction.equals("leftDown")) { // <v
            world[hingeX][hingeY + 1] = Tileset.WALL;
            world[hingeX + 1][hingeY + 1] = Tileset.WALL;
            world[hingeX + 1][hingeY] = Tileset.WALL;
        }
    }

    /** Creates a dead-end, either vertically or horizontally.
     *  @param direction is either left, right, up, down
     */
    public void deadEnd(Position start, String direction, TETile[][] world) {
        if (!directionsStraight.contains(direction)) {
            throw new IllegalArgumentException("Cannot place a dead end here. " + direction
                    + " is not a valid direction to feed into a dead end.");
        }
        int horizPos = start.getX();
        int vertPos = start.getY();

        if (direction.equals("left") || direction.equals("right")) {
            for (int y = 0; y < 3; y += 1) {
                world[horizPos][vertPos - 1 + y] = Tileset.WALL;
            }
        } else {
            for (int x = 0; x < 3; x += 1) {
                world[horizPos - 1 + x][vertPos] = Tileset.WALL;
            }
        }
    }

}
