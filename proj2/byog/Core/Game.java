package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

////        Original Method backup (in case i need to restart)
//        TETile[][] finalWorldFrame = null;
//        return finalWorldFrame;

        TETile[][] finalWorldFrame = null;

        if (input.startsWith("N")) {
            TETile[][] world = new TETile[WIDTH][HEIGHT];
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    world[x][y] = Tileset.NOTHING;
                }
            }

            long SEED = Integer.valueOf(input.substring(1, input.length() - 2));
            Random RANDOM = new Random(SEED);

            RoomGenerator rg = new RoomGenerator();
            while (rg.getRoomList().isEmpty()) {
                int posX = RandomUtils.uniform(RANDOM, WIDTH);
                int posY = RandomUtils.uniform(RANDOM, HEIGHT);
                Position roomLocation = new Position(posX, posY);
                int roomWidth = RandomUtils.uniform(RANDOM, 3, WIDTH / 3);
                int roomHeight = RandomUtils.uniform(RANDOM, 3, HEIGHT / 3);
                rg.makeRoom(world, roomLocation, roomWidth, roomHeight);
            }

//            for (Room r : rg.getRoomList()) {  //add doors to all the rooms that exist
//                r.makeDoors(world, RANDOM);
//
//
//            }

            finalWorldFrame = world;
        }

        //render here?
        ter.initialize(WIDTH, HEIGHT);

        //Draw the world to the screen
        ter.renderFrame(finalWorldFrame);

        return finalWorldFrame;

        /**Plan for building the world:
         * Start with making one room
         * build hallways at the doors
         * decide using whereToNext whether to make deadends, rooms, or more hallways
         * Finish after a certain number of cycles of this
         *
         * Question: does a "doorway" tile count as a gap between connecting a room and hallway?
         * */

    }
}
