package byog.Core.Trash;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// THIS IS OUR OLD WORLD GENERATION APPROACH (branching tree of rooms and hallways)
// SWITCHING TO A SIMPLER METHOD

public class WorldGeneratorOLD {
    /** Our plan for how to approach WorldGeneration algorithm.
     *  @Source http://pcg.wikidot.com/pcg-algorithm:dungeon-generation
     *  Use a branching tree algorithm:
     *  Start by creating a single room and then branching hallways off of that room going for a
     *  random distance in a direction and then deciding whether to continue, change directions,
     *  dead end, or create a new room at the end of that hallway.
     *  small detail:
     */

    Random randomGenerator;
    List<String> fromLeftTurns;
    List<String> fromRightTurns;
    List<String> fromUpTurns;
    List<String> fromDownTurns;

    public WorldGeneratorOLD(Random random) {
        this.randomGenerator = random;

        fromLeftTurns = new ArrayList<>(2);
        fromLeftTurns.add("rightUp");
        fromLeftTurns.add("rightDown");

        fromRightTurns = new ArrayList<>(2);
        fromRightTurns.add("leftUp");
        fromRightTurns.add("leftDown");

        fromDownTurns = new ArrayList<>(2);
        fromDownTurns.add("rightUp");
        fromDownTurns.add("leftUp");

        fromUpTurns = new ArrayList<>(2);
        fromUpTurns.add("leftDown");
        fromUpTurns.add("rightDown");

    }

    /** A method that is used by WorldGenerator to start making hallways from a room. Will randomly
     *  determine whether to build a hallway, turn, make a dead-end, or build a room.
     * NOTE: to start a call to randomHallway, it needs to be given a
     * @param next = a WhereToNext object that specifies how next piece should be built.
     * @param random = a Random made with initial seed.
     * @return = a new WhereToNext that randomHallways can use to recursively call itself.
     */
//    public void randomHallways(WhereToNext next, Random random, TETile[][] world) {
//
//        HallwayGenerator hg = new HallwayGenerator();
//        RoomGenerator rg = new RoomGenerator();
//        WhereToNext newNext;
//        int decision = RandomUtils.uniform(random, 0, 9); // Change to allow more options
//
//        // Option 0: build hallway (4/9)
//        if (decision >= 0 && decision < 4) {
//            int length = RandomUtils.uniform(random, 1, 6);
//
// WhereToNext destination = new WhereToNext(next.getNextDirection(),
// next.getNextPosition(), length, world);
// I realized I could use WhereToNext constructor to help me get a Position
// for purpose of checking
//        // if hallway would be unobstructed or not.
//
//            Position nextP = next.getNextPosition(); // To make debugging easier
//            Position destinationP = destination.getNextPosition();
//
//            if (! nextP.unobstructedHallway(destinationP, world)) {
//                randomHallways(next, random, world);
//            }
//
//        newNext = hg.buildHallway(next.getNextPosition(), length, next.getNextDirection(), world);
//            randomHallways(newNext, random, world); // Recursively call
//        }
//
//        // Option 1: dead-end (2/9)
//        if (decision >= 4 && decision < 6) { // could make this an else statement
//            hg.deadEnd(next.getNextPosition(), next.getNextDirection(), world);
//        }
//
//        // Option 2: turn (3/9)
//        if (decision >= 5 && decision < 9) {
//            if (next.getNoCorner()) { // If corners not allowed, recursively call and try again
//                randomHallways(next, random, world);
//            }
//
//            String typeOfTurn = whereToTurn(next, random);
//            newNext = hg.buildHallway(next.getNextPosition(), 0, typeOfTurn, world);
//            randomHallways(newNext, random, world); // Recursively call
//        }
//
//        // Option 3: build room
//  rg.makeRoom(Position start, String direction-hallway-feeding-into-it,
//  TETile[][] world, Random random)
//
//    }
//
//
//    private String whereToTurn(WhereToNext next, Random random) {
//        int choice = RandomUtils.uniform(random, 0, 2);
//
//        if (next.getNextDirection() == "right") {
//            return fromRightTurns.get(choice);
//        }
//        if (next.getNextDirection() == "left") {
//            return fromLeftTurns.get(choice);
//        }
//        if (next.getNextDirection() == "up") {
//            return fromUpTurns.get(choice);
//        } else { // assume next.getNextDirection() == "down"
//            return fromDownTurns.get(choice);
//        }
//    }


//    /** Checks whether there are any WALLS or FLOORS where a hallway is planned.
//     *  Assume that this and @param two are either horizontally aligned or vertically aligned.
//     *
//     *  NOTE: THIS METHOD IS BUGGY AND BAD. DO NOT USE!!!!
//     *
//     */
//    public Boolean unobstructedHallway(Position two, TETile[][] world) {
//
//        if (outOfThisWorld(two, world)) {
//            return false;
//        }
//        if (unalignedPositionsForUnobstructed(two)) {
//            return false;
//        }
//
//        if (this.getX() > two.getX()) { // Allows user to not worry about
// which Position is "lower"
//            return two.unobstructedHallway(this, world);
//        } else if ((this.getX() == two.getX()) && (this.getY() > two.getY())) {
//            return two.unobstructedHallway(this, world);
//        }
//
//        if (two.getY() - this.getY() == 0) { // Horizontal alignment
//            for (int xPos = this.getX() + 1; xPos <= two.getX(); xPos += 1) {
//                if (world[xPos][this.getY()].equals(Tileset.WALL)
//                        || world[xPos][this.getY()].equals(Tileset.FLOOR)) {
//                    return false;
//                }
//            }
//        } else if (two.getX() - this.getX() == 0) { // Vertical alignment
//            for (int yPos = this.getY() + 1; yPos <= two.getY(); yPos += 1) {
//                if (world[this.getX()][yPos].equals(Tileset.WALL)
//                        || world[this.getX()][yPos].equals(Tileset.FLOOR)) {
//                    return false;
//                }
//            }
//        } else {
//            return true;
//        }
//        return true;
//    }
//
//
//    private boolean outOfThisWorld(Position two, TETile[][] world) {
//        if ((two.getX() > world.length) || (two.getY() > world[0].length)
//                || this.getX() > world.length || this.getY() > world[0].length) {
//            // Position two outside world
//            return true;
//        }
//        return false;
//    }
//
//    private boolean unalignedPositionsForUnobstructed(Position two) {
//        if ((two.getX() - this.getX()) != 0 && (two.getY() - this.getY()) != 0) {
////            throw new IllegalArgumentException("Positions one and two are not horizontally" +
////                    "aligned or vertically aligned.");
//            return true;
//        }
//        return false;
//    }

}
