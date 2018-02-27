package byog.Core;


import byog.TileEngine.TETile;

import java.util.HashSet;
import java.util.Set;

/** An object to be returned by buildHallway method above. Tells WorldGeneration algorithm where
 *  to start the next build and in what direction the current hallway is pointed.
 */
public class WhereToNext {

    private static Set<String> DirectionsStraight;

    private String newDirection;
    private Position newPosition;
    private Boolean noRoom; // True if a room can't go here (ie. just turned a corner)
    private Boolean noCorner; // True if a corner can't go here (ie. just turned a corner)

    public WhereToNext(String direction, Position start, int distance, TETile[][] world) {
        // TODO: use unobstructedHallway method to figure out for corners the next direction
        // TODO: make sure newPosition is 1 away from wherever buildHallway finished

        DirectionsStraight = new HashSet<>();
        DirectionsStraight.add("left");
        DirectionsStraight.add("right");
        DirectionsStraight.add("up");
        DirectionsStraight.add("down");

        if (DirectionsStraight.contains(direction)) {
            newDirection = direction;
            noRoom = false;
            noCorner = false;
            nextPositionStraight(direction, start, distance);


        } else { // just turned a corner
            noRoom = true;
            noCorner = true;
            nextPositionDirectionCorner(direction, start, world);
        }
    }

    public String getNextDirection() {
        return this.newDirection;
    }
    public Position getNextPosition() {
        return this.newPosition;
    }
    public Boolean getNoRoom() {
        return this.noRoom;
    }
    public Boolean getNoCorner() {
        return this.noCorner;
    }




    /** Helper method to return the next Position for WhereToNext object.
     *  This is for straights.
     */
    private void nextPositionStraight(String direction, Position start, int distance) {
        this.newPosition = new Position(0, 0); // arbitrary

        if (direction == "right") {
            this.newPosition = new Position((start.getX() + distance), start.getY());
        }
        if (direction == "left") {
            this.newPosition = new Position((start.getX() - distance), start.getY());
        }
        if (direction == "up") {
            this.newPosition = new Position(start.getX(), (start.getY() + distance));
        }
        if (direction == "down") {
            this.newPosition = new Position(start.getX(), (start.getY() - distance));
        }
    }


    /** Helper method to return the next Position and direction for WhereToNext object.
     *  This is for corners.
     */
    private void nextPositionDirectionCorner(String direction, Position start, TETile[][] world) {
        Position goingUp = new Position(start.getX(), start.getY() + 1);
        Position goingDown = new Position(start.getX(), start.getY() - 1);
        Position goingLeft = new Position(start.getX() - 1, start.getY());
        Position goingRight = new Position(start.getX() + 1, start.getY());

        if (direction == "rightUp") {
            if (start.unobstructedHallway(goingUp, world)) {
                this.newPosition = goingUp;
                this.newDirection = "up";
            } else {
                this.newPosition = goingRight;
                this.newDirection = "right";
            }

        }
        if (direction == "leftUp") {
            if (start.unobstructedHallway(goingUp, world)) {
                this.newPosition = goingUp;
                this.newDirection = "up";
            } else {
                this.newPosition = goingLeft;
                this.newDirection = "left";
            }

        }
        if (direction == "rightDown") {
            if (start.unobstructedHallway(goingRight, world)) {
                this.newPosition = goingRight;
                this.newDirection = "right";
            } else {
                this.newPosition = goingDown;
                this.newDirection = "down";
            }

        }
        if (direction == "leftDown") {
            if (start.unobstructedHallway(goingLeft, world)) {
                this.newPosition = goingLeft;
                this.newDirection = "left";
            } else {
                this.newPosition = goingDown;
                this.newDirection = "down";
            }
        }
    }
}
