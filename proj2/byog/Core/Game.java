package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

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

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        drawMenu();

        String typing = "";
        while (typing.length() < 50000000) {  //only exit the program
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char input = StdDraw.nextKeyTyped();
            if (input == 'q' || input == 'Q') {
                System.exit(0);
                break;
            } else if (input == 'n' || input == 'N') {
                /** TODO: Pop out a window to get the seed,
                 * if valid, then call Ben's code for playing the Game using the seed
                 * I think we should put his code in the playNewGame method in this class
                 * */

                //make window
                //call playNewGame(); if valid seed



            } else if (input == 'l' || input == 'L') {
                /** TODO: Load the previous game that was terminated
                 * and its random seed*/
            } else {
                Font font = new Font("Monaco", Font.PLAIN, 12);
                StdDraw.setFont(font);
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "Not a valid command");
                StdDraw.show();
            }
            typing += input;
        }
    }



//        while (input.length() < n) {
//            if (!StdDraw.hasNextKeyTyped()) {
//                continue;
//            }
//            char key = StdDraw.nextKeyTyped();
//            input += String.valueOf(key);
//            drawFrame(input);
//        }




//
//        if (input.startsWith("N") || input.startsWith("n")) {
//            playWithInputString(input);
//
//
//        }





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


        TETile[][] world = new TETile[WIDTH][HEIGHT];

        if (input.startsWith("N") || input.startsWith("n")) {
//            TETile[][] world = new TETile[WIDTH][HEIGHT];
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    world[x][y] = Tileset.NOTHING;
                }
            }

            //TODO: update this to also take in direction inputs for character, quitting, loading
            long seed = Long.parseLong(input.substring(1, input.length() - 2));
            Random random = new Random(seed);

            RoomGenerator rg = new RoomGenerator();
            for (int i = 0; i < 50; i += 1) {   // make up to 50 rooms in the world;
                                                // some will overlap and fail to exist
                int posX = RandomUtils.uniform(random, WIDTH);
                int posY = RandomUtils.uniform(random, HEIGHT);
                Position roomLocation = new Position(posX, posY);
                int roomWidth = RandomUtils.uniform(random, 4, WIDTH / 4);
                int roomHeight = RandomUtils.uniform(random, 4, HEIGHT / 4);
                rg.makeRoom(world, roomLocation, roomWidth, roomHeight);
            }


            HallwayGenerator hg = new HallwayGenerator(random);
            hg.connectRoomsStraight(rg.getRoomList(), rg.getCornerBlacklist(), world);

        }

        return world;
    }


    public void drawMenu() {
        StdDraw.setCanvasSize(WIDTH * 16, HEIGHT* 16);
        StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);


        Font font1 = new Font("Helvetica", Font.BOLD, 45);
        Font font2 = new Font("Monaco", Font.BOLD, 30);
        Font font3 = new Font("Monaco", Font.PLAIN, 18);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);

        StdDraw.setFont(font1);
        StdDraw.text(WIDTH / 2, 3 * HEIGHT / 4, "Pika-Man");
        StdDraw.setFont(font2);
        StdDraw.text(WIDTH / 2, 2 * HEIGHT / 3, "CS 61B Project 2");
        StdDraw.setFont(font3);
        StdDraw.text(WIDTH / 2, 7 * HEIGHT / 12, "Created by Ben Sydserff & Danny Siu");


        StdDraw.text(WIDTH / 2, 6 * HEIGHT / 17, "New Game (N)");
        StdDraw.text(WIDTH / 2, 5 * HEIGHT / 16, "Load Game (L)");
        StdDraw.text(WIDTH / 2, 4 * HEIGHT / 16, "Quit (Q)");


        StdDraw.show();

    }


    public void playNewGame() {
        // insert Ben's code for playing New Game
    }


}
