package byog.Core;


import byog.TileEngine.TETile;

import java.util.HashSet;
import java.util.Set;

/** An object to be returned by buildHallway method above. Tells WorldGeneration algorithm where
 *  to start the next build and in what direction the current hallway is pointed.
 */
public class WhereToNext {

    private Set<String> DirectionsStraight;
    private Set<String> DirectionsCorner;

    String newDirection;
    Position newPosition;
    Boolean noRoom; // True if a room can't go here (ie. just turned a corner)
    Boolean noCorner; // True if a corner can't go here (ie. just turned a corner)

    private WhereToNext(String direction, Position start, int distance, TETile[][] world) {
        // TODO: use unobstructedHallway method to figure out for corners the next direction
        // TODO: make sure newPosition is 1 away from wherever buildHallway finished

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

        if (DirectionsStraight.contains(direction)) {
            newDirection = direction;
            noRoom = false;
            noCorner = false;
            newPosition = nextPositionStraight(direction, start, distance);


        } else { // just turned a corner
            noRoom = true;
            noCorner = true;
            newDirection = "not sure yet";

            if (direction == "rightUp") {



            }
            if (direction == "leftUp") {



            }
            if (direction == "rightDown") {



            }
            if (direction == "leftDown") {



            }



        }

    }
    /** Helper method to return the next Position for WhereToNext object.
     *  This is for straights.
     */
    private Position nextPositionStraight(String direction, Position start, int distance) {
        Position newPosition = new Position(0, 0); // arbitrary

        if (direction == "right") {
            newPosition = new Position((start.getX() + distance + 1), start.getY());
        }
        if (direction == "left") {
            newPosition = new Position((start.getX() - distance - 1), start.getY());
        }
        if (direction == "up") {
            newPosition = new Position(start.getX(), (start.getY() + distance + 1));
        }
        if (direction == "down") {
            newPosition = new Position(start.getX(), (start.getY() - distance - 1));
        }
        return newPosition;
    }



}
