package byog.Core.Trash;

import java.util.Random;

public class WorldGenerator {
    /**
     * @Source friend and classmate Andrew Chen. He told us how his partner
     * and him conceptually built their world.
     * Explanation of why change: Our plan changed on Tuesday, roughly 12 PM
     * (after Phase 1 supposed to be due).
     * Original plan was a branching-tree algorithm starting w/ a single random room,
     * and building hallways off of it that would randomly turn, continue, dead-end,
     * or build a room at the end of them.
     * Ran into bugs with algorithm to randomly decide how to turn & build hallways,
     * decided to switch to a simpler approach
     * (considering we were already past the deadline).
     *
     * Our plan for how to approach WorldGeneration algorithm:
     * Step 1.) Randomly generate rooms
     * Step 2.) Iterate through all rooms
     * Step 3.) Iterate through perimeter, see if rooms can be connected to a wall
     *          away from them.
     * Step 4.) At end, any unconnected rooms should be connected
     *         SOMEHOW (hallways that turn?)
     *       > This step might not be needed; included in Step 5.) (?)
     * Step 5.) After all/most rooms connected, make some random hallways that
     *         turn and dead-end
     */

    Random randomGenerator;

    public WorldGenerator(Random random) {
        randomGenerator = random;
    }

}
