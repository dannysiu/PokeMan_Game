package byog.Core.Trash;

import byog.TileEngine.TETile;
import java.util.ArrayList;
import java.util.Random;
import byog.Core.Position;
import byog.Core.Room;
import byog.Core.HallwayGenerator;

public class MakeHallwayV2 {


    /** The second method for making a hallway.
     * Takes in 2 rooms and builds a turning hallway between them
     */
    public void buildHallway(TETile[][] world, Room r1, Room r2) {
        ArrayList<Position> border1 = r1.getPerimeterList();
        ArrayList<Position> border2 = r2.getPerimeterList();

        boolean hallwayBuilt = false;
        while (!hallwayBuilt) {
            Position p1 = border1.get(0);
            Position p2 = border2.get(1);
            int distX = p1.getX() - p2.getX();
            int distY = p1.getY() - p2.getY();

            String directionX = "";
            String directionY = "";
            if (distX < 0) {
                directionX = "right";
            } else if (distX > 0) {
                directionX = "left";
            }
            if (distY < 0) {
                directionY = "up";
            } else if (distY > 0) {
                directionY = "down";
            }

            Random random = new Random();
            HallwayGenerator hg = new HallwayGenerator(random);
            hg.buildHallway(p1, Math.abs(distX), directionX, world);
            Position corner = new Position(p1.getX() - distX - 1, p1.getY());
            if (directionX.equals("left")) {
                hg.buildHallway(corner, Math.abs(distY) - 1, directionY, world);
            } else if (directionX.equals("right")) {
                hg.buildHallway(corner, Math.abs(distY) - 1, directionY, world);
            }
            hallwayBuilt = true;
        }


//                for reference:
// buildHallway(Position start, int distance, String direction, TETile[][] world)

//
//        for (Position p1 : border1) {
//            for (Position p2 : border2) {
//                int distX = p1.getX() - p2.getX();
//                int distY = p1.getY() - p2.getY();
//
//                String directionX = "";
//                String directionY = "";
//                if (distX < 0) {
//                    directionX = "right";
//                } else if (distX > 0) {
//                    directionX = "left";
//                }
//                if (distY < 0) {
//                    directionY = "up";
//                } else if (distY > 0) {
//                    directionY = "down";
//                }
//            }
//        }

    }

}
