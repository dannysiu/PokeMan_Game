package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenerator {
    /** Our plan for how to approach WorldGeneration algorithm.
     *  @Source http://pcg.wikidot.com/pcg-algorithm:dungeon-generation
     *  Use a branching tree algorithm:
     *  Start by creating a single room and then branching hallways off of that room going for a
     *  random distance in a direction and then deciding whether to continue, change directions,
     *  dead end, or create a new room at the end of that hallway.
     *  TODO: implement some sort of stop condition so that we don't use all of the dead space.
     *  TODO: figure out what to do about the unlocked doors at the end of world generation
     *  small detail:
     *  TODO: at the end of world building, check for doors at the world perimeter and set to wall
     */

    long seed;
    List<String> fromLeftTurns;
    List<String> fromRightTurns;
    List<String> fromUpTurns;
    List<String> fromDownTurns;

    public WorldGenerator(long userSeed) {
        this.seed = userSeed;
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

        // TODO: Make a starting random room.
        // TODO: Make starting WhereToNext objects based on random rooms and where unlocked doors are
    }

    /** A method that is used by WorldGenerator to start making hallways from a room. Will randomly
     *  determine whether to build a hallway, turn, make a dead-end, or build a room.
     * NOTE: to start a call to randomHallway, it needs to be given a
     * @param next = a WhereToNext object that specifies how next piece should be built.
     * @param random = a Random made with initial seed.
     * @return = a new WhereToNext that randomHallways can use to recursively call itself.
     */
    public WhereToNext randomHallways(WhereToNext next, Random random) {

        HallwayGenerator hg = new HallwayGenerator();
        RoomGenerator rg = new RoomGenerator();
        int decision = RandomUtils.uniform(random, 0, 3);

        // Option 0: build hallway
        if (decision == 0) {
            int length = RandomUtils.uniform(random, 1, 6);
            try {
//                Position destination = new Position();

            } catch (IllegalArgumentException e) {
                // do something
            }


        }

        // Option 1: turn

        // Option 2: build room

        // Option 3: dead-end


        return null;
    }



}
