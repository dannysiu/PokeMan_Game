//package byog.Core.Trash;
//
//
//import byog.Core.Position;
//import byog.TileEngine.TETile;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//// DEPRECATED! DO NOT USE! SWITCHING APPROACH ENTIRELY
//
//
///** An object to be returned by buildHallway method above. Tells WorldGeneration algorithm where
// *  to start the next build and in what direction the current hallway is pointed.
// */
//public class WhereToNext {
//
//    private static Set<String> STRAIGHTDIRECTIONS;
//
//    private String newDirection;
//    private Position newPosition;
//    private Boolean noRoom; // True if a room can't go here (ie. just turned a corner)
//    private Boolean noCorner; // True if a corner can't go here (ie. just turned a corner)
//
//
//    /** Constructor for making a WhereToNext object. Returned after each call to buildHallway
//     *  in order to know where next hallway/dead-end/room can be built.
//     *
//     * @param direction = direction that build was going towards.
//     * @param start = position where previous build had started
//     * @param distance = how far previous build went
//     * @param world
//     */
//    public WhereToNext(String direction, Position start, int distance, TETile[][] world) {
//        // NEED TO: use unobstructedHallway method to figure out for corners the next direction
//
//        STRAIGHTDIRECTIONS = new HashSet<>();
//        STRAIGHTDIRECTIONS.add("left");
//        STRAIGHTDIRECTIONS.add("right");
//        STRAIGHTDIRECTIONS.add("up");
//        STRAIGHTDIRECTIONS.add("down");
//
//        if (STRAIGHTDIRECTIONS.contains(direction)) {
//            newDirection = direction;
//            noRoom = false;
//            noCorner = false;
//            nextPositionStraight(direction, start, distance);
//
//
//        } else { // just turned a corner
//            noRoom = true;
//            noCorner = true;
//            nextPositionDirectionCorner(direction, start, world);
//        }
//    }
//
//    /** Overloaded WhereToNext constructor for forcing noRoom and noCorner to be true.
//     *  Used for initial WhereToNext object in a test or immediately after starting off of a room.
//     */
//    public WhereToNext(String direction, Position start, Boolean noCorner,
//                       Boolean noRoom, TETile[][] world) {
//
//        STRAIGHTDIRECTIONS = new HashSet<>();
//        STRAIGHTDIRECTIONS.add("left");
//        STRAIGHTDIRECTIONS.add("right");
//        STRAIGHTDIRECTIONS.add("up");
//        STRAIGHTDIRECTIONS.add("down");
//
//        int distance = 0;
//
//        if (!STRAIGHTDIRECTIONS.contains(direction)) {
//            throw new IllegalArgumentException(direction + " is not a valid starting direction.");
//        }
//        this.newDirection = direction;
//        this.noRoom = noRoom;
//        this.noCorner = noRoom;
//        nextPositionStraight(direction, start, distance);
//    }
//
//
//    public String getNextDirection() {
//        return this.newDirection;
//    }
//    public Position getNextPosition() {
//        return this.newPosition;
//    }
//    public Boolean getNoRoom() {
//        return this.noRoom;
//    }
//    public Boolean getNoCorner() {
//        return this.noCorner;
//    }
//
//
//
//
//    /** Helper method to return the next Position for WhereToNext object.
//     *  This is for straights.
//     *  UPDATE: used to be private, but made public so that WorldGenerator randomHallway
//     *  algorithm could use it
//     */
//    public void nextPositionStraight(String direction, Position start, int distance) {
//        this.newPosition = new Position(0, 0); // arbitrary
//
//        if (direction.equals("right")) {
//            this.newPosition = new Position((start.getX() + distance), start.getY());
//        }
//        if (direction.equals("left")) {
//            this.newPosition = new Position((start.getX() - distance), start.getY());
//        }
//        if (direction.equals("up")) {
//            this.newPosition = new Position(start.getX(), (start.getY() + distance));
//        }
//        if (direction.equals("down")) {
//            this.newPosition = new Position(start.getX(), (start.getY() - distance));
//        }
//    }
//
//
//    /** Helper method to return the next Position and direction for WhereToNext object.
//     *  This is for corners.
//     */
//    private void nextPositionDirectionCorner(String direction, Position start, TETile[][] world) {
//        Position goingUp = new Position(start.getX(), start.getY() + 1);
//        Position goingDown = new Position(start.getX(), start.getY() - 1);
//        Position goingLeft = new Position(start.getX() - 1, start.getY());
//        Position goingRight = new Position(start.getX() + 1, start.getY());
//
//        if (direction.equals("rightUp")) {
//            if (start.unobstructedHallway(goingUp, world)) {
//                this.newPosition = goingUp;
//                this.newDirection = "up";
//            } else {
//                this.newPosition = goingRight;
//                this.newDirection = "right";
//            }
//
//        }
//        if (direction.equals("leftUp")) {
//            if (start.unobstructedHallway(goingUp, world)) {
//                this.newPosition = goingUp;
//                this.newDirection = "up";
//            } else {
//                this.newPosition = goingLeft;
//                this.newDirection = "left";
//            }
//
//        }
//        if (direction.equals("rightDown")) {
//            if (start.unobstructedHallway(goingRight, world)) {
//                this.newPosition = goingRight;
//                this.newDirection = "right";
//            } else {
//                this.newPosition = goingDown;
//                this.newDirection = "down";
//            }
//
//        }
//        if (direction.equals("leftDown")) {
//            if (start.unobstructedHallway(goingLeft, world)) {
//                this.newPosition = goingLeft;
//                this.newDirection = "left";
//            } else {
//                this.newPosition = goingDown;
//                this.newDirection = "down";
//            }
//        }
//    }
//}
