package byog.Core;

import byog.TileEngine.TETile;

/** Use this class to create and store positions for our game objects
 * For example, store positions for the corners of rooms and doorways for hallway connection
 * */

public class Position implements java.io.Serializable {
    private static final long serialVersionUID = 748847483673212345L;
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

//    @Override
//    public boolean equals(Object obj) {
//        Position p = (Position) obj;
//        return (this.getX() == p.getX() && this.getY() == p.getY());
//    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    /** Given a particular Tileset, this method should return the # of that tileset that exist
     *  around Position this.
     * @param neighbor = A Tileset type (ex: Tileset.FLOOR, Tileset.WALL, etc.)
     */
    public int adjacentEight(TETile neighbor, TETile[][] world) {
        int numNeighbors = 0;
        for (int xDiff = -1; xDiff <= 1; xDiff += 1) {
            for (int yDiff = -1; yDiff <= 1; yDiff += 1) {
                if (yDiff != 0 || xDiff != 0) {
                    if (world[this.x + xDiff][this.y + yDiff] == neighbor) {
                        numNeighbors += 1;
                    }
                }
            }
        }
        return numNeighbors;
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



