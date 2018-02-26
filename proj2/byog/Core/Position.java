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


    /** Checks whether there are any WALLS or FLOORS where a hallway is planned.
     *  Assume that this and @param two are either horizontally aligned or vertically aligned.
     *  Assume that the left-most or bottom-most Position is this.
     */
    public boolean unobstructedHallway(Position two, TETile[][] world) {

        if ((two.getX() - this.getX()) != 0 || (two.getY() - this.getY()) != 0) {
            throw new IllegalArgumentException("Positions one and two are not horizontally" +
                    "aligned or vertically aligned.");
        }

        for (int x = this.getX(); x <= two.getX(); x += 1) {
            for (int y = this.getY(); y <= two.getY(); y += 1) {
                if (world[x][y].equals(Tileset.WALL) || world[x][y].equals(Tileset.FLOOR)) {
                    return false;
                }
            }
        }
        return true;
    }

}
