package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/** Use this class to create and store positions for our game objects
 * For example, store positions for the corners of rooms and doorways for hallway connection
 * */

public class Position {
    private int x;
    private int y;

    public Position(int coord_x, int coord_y) {
        x = coord_x;
        y = coord_y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double calcDistance(Position p) {
        double x_dist = (this.getX() - p.getX()) * (this.getX() - p.getX());
        double y_dist = (this.getY() - p.getY()) * (this.getY() - p.getY());
        return Math.sqrt(x_dist + y_dist);
    }

    public boolean equals(Position p) {
        return (this.getX() == p.getX() && this.getY() == p.getY());
    }


    /** Checks whether there are any WALLS or FLOORS where a hallway is planned.
     *  Assume that this and @param two are either horizontally aligned or vertically aligned.
     */
    public boolean unobstructedHallway(Position two, TETile[][] world) {

        if ((two.getX() - this.getX()) != 0 && (two.getY() - this.getY()) != 0) {
            throw new IllegalArgumentException("Positions one and two are not horizontally" +
                    "aligned or vertically aligned.");
        }

        if (this.getX() > two.getX()) { // Allows user to not worry about which Position is "lower"
            return two.unobstructedHallway(this, world);
        } else if ((this.getX() == two.getX()) && (this.getY() > two.getY())) {
            return two.unobstructedHallway(this, world);
        }

        if (two.getY() - this.getY() == 0) { // Horizontal alignment
            for (int x = this.getX() + 1; x <= two.getX(); x += 1) {
                if (world[x][this.getY()].equals(Tileset.WALL) ||
                        world[x][this.getY()].equals(Tileset.FLOOR)) {
                    return false;
                }

            }
        } else if (two.getX() - this.getX() == 0) { // Vertical alignment
            for (int y = this.getY() + 1; y <= two.getY(); y += 1) {
                if (world[this.getX()][y].equals(Tileset.WALL) ||
                        world[this.getX()][y].equals(Tileset.FLOOR)) {
                    return false;
                }
            }
        } else {
            return true;
        }



//        for (int x = this.getX(); x <= two.getX(); x += 1) {
//            for (int y = this.getY(); y <= two.getY(); y += 1) {
//                if (world[x][y].equals(Tileset.WALL) || world[x][y].equals(Tileset.FLOOR)) {
//                    System.out.printf("Obstructed at (%d, %d)\n", x, y);
//                    return false;
//                }
//            }
//        }
        return true;
    }

}
