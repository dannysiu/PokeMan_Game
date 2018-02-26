package byog.Core;

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
}
