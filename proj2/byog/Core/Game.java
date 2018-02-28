package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter;
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;

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
        // Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

////        Original Method backup (in case i need to restart)
//        TETile[][] finalWorldFrame = null;
//        return finalWorldFrame;

        ter = new TERenderer();
//        ter.initialize(WIDTH, HEIGHT);

//
//        TETile[][] finalWorldFrame = null;

        TETile[][] world = new TETile[WIDTH][HEIGHT];

        if (input.startsWith("N")) {
//            TETile[][] world = new TETile[WIDTH][HEIGHT];
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    world[x][y] = Tileset.NOTHING;
                }
            }

            long seed = Integer.valueOf(input.substring(1, input.length() - 2));
            Random random = new Random(seed);

            RoomGenerator rg = new RoomGenerator();
            for (int i = 0; i < 50; i += 1) {   // make up to 50 rooms in the world;
                                                // some will overlap and fail to exist
                int posX = RandomUtils.uniform(random, WIDTH);
                int posY = RandomUtils.uniform(random, HEIGHT);
                Position roomLocation = new Position(posX, posY);
                int roomWidth = RandomUtils.uniform(random, 4, WIDTH / 3);
                int roomHeight = RandomUtils.uniform(random, 4, HEIGHT / 3);
                rg.makeRoom(world, roomLocation, roomWidth, roomHeight);
            }


            HallwayGenerator hg = new HallwayGenerator(random);
            hg.connectRoomsStraight(rg.getRoomList(), rg.getCornerBlacklist(), world);

//            finalWorldFrame = world;
        }


        //Draw the world to the screen
//        ter.renderFrame(world);

        return world;


        /**Plan for building the world:
         * Generate all the rooms
         * For each room, try to connect to other rooms via one hallway
         * Make corner intersections
         * */

    }
}
