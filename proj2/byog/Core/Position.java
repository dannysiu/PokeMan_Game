package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/** Use this class to create and store positions for our game objects
 * For example, store positions for the corners of rooms and doorways for hallway connection
 * */

public class Position {
    private int x;
    private int y;

    public Position(int coordX, int coordY) {
        if ((coordX < 0) || (coordY < 0)) {
            throw new IllegalArgumentException("CoordX: " + coordX + " or CoordY: " + coordY
                    + " is outside bounds of an allowed Position.");
        }
        x = coordX;
        y = coordY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double calcDistance(Position p) {
        double xDist = (this.getX() - p.getX()) * (this.getX() - p.getX());
        double yDist = (this.getY() - p.getY()) * (this.getY() - p.getY());
        return Math.sqrt(xDist + yDist);
    }

    public boolean equals(Position p) {
        return (this.getX() == p.getX() && this.getY() == p.getY());
    }


    /** Checks whether there are any WALLS or FLOORS where a hallway is planned.
     *  Assume that this and @param two are either horizontally aligned or vertically aligned.
     *
     *  NOTE: THIS METHOD IS BUGGY AND BAD. DO NOT USE!!!!
     *
     */
    public Boolean unobstructedHallway(Position two, TETile[][] world) {

        if (outOfThisWorld(two, world)) {
            return false;
        }
        if (unalignedPositionsForUnobstructed(two)) {
            return false;
        }

        if (this.getX() > two.getX()) { // Allows user to not worry about which Position is "lower"
            return two.unobstructedHallway(this, world);
        } else if ((this.getX() == two.getX()) && (this.getY() > two.getY())) {
            return two.unobstructedHallway(this, world);
        }

        if (two.getY() - this.getY() == 0) { // Horizontal alignment
            for (int xPos = this.getX() + 1; xPos <= two.getX(); xPos += 1) {
                if (world[xPos][this.getY()].equals(Tileset.WALL)
                        || world[xPos][this.getY()].equals(Tileset.FLOOR)) {
                    return false;
                }
            }
        } else if (two.getX() - this.getX() == 0) { // Vertical alignment
            for (int yPos = this.getY() + 1; yPos <= two.getY(); yPos += 1) {
                if (world[this.getX()][yPos].equals(Tileset.WALL)
                        || world[this.getX()][yPos].equals(Tileset.FLOOR)) {
                    return false;
                }
            }
        } else {
            return true;
        }
        return true;
    }


    private boolean outOfThisWorld(Position two, TETile[][] world) {
        if ((two.getX() > world.length) || (two.getY() > world[0].length)
                || this.getX() > world.length || this.getY() > world[0].length) {
            // Position two outside world
            return true;
        }
        return false;
    }

    private boolean unalignedPositionsForUnobstructed(Position two) {
        if ((two.getX() - this.getX()) != 0 && (two.getY() - this.getY()) != 0) {
//            throw new IllegalArgumentException("Positions one and two are not horizontally" +
//                    "aligned or vertically aligned.");
            return true;
        }
        return false;
    }

}

//    public boolean freeSpaceMaybe(String direction, int distance, TETile[][] world) {
//        int worldWidth = world.length;
//        int worldHeight = world[0].length;
//        boolean verdict;
//
//        if (direction == "right") {
//
//        }
//        if (direction == "left") {
//
//        }
//        if (direction == "up") {
//
//        }
//        if (direction == "down") {
//
//        }
//
//        return verdict = false; // place holder
//    }



