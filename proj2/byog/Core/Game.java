package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

import java.util.Random;

public class Game {
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;




    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

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
            Random randomGenerator = new Random(seed);

            RoomGenerator rg = new RoomGenerator(randomGenerator);
            rg.populateRooms(world);


            HallwayGenerator hg = new HallwayGenerator(randomGenerator);
            hg.connectRoomsStraight(rg.getRoomList(), world);

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


    public static void playNewGame(Random random, TETile[][] world) {
        // TODO: insert Ben's code for playing New Game

        boolean gameOver = false;
        TERenderer ter = new TERenderer();

        // Drawing the world map
        RoomGenerator rg = new RoomGenerator(random);
        HallwayGenerator hg = new HallwayGenerator(random);

        rg.populateRooms(world);
        hg.connectRoomsStraight(rg.getRoomList(), world);

        ter = new TERenderer();
        ter.initialize(world.length, world[0].length + 3, 0, 0);
        ter.renderFrame(world);
        // Finished drawing world map

        // Adding players to world map
        Player player = new Player(random, ter, world);
        // Finished adding players

        // Gameplay loop
        while (!gameOver) {

            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }

            char command = StdDraw.nextKeyTyped();
            if (command == 'q' || command == 'Q') {
                gameOver = true;
                // TODO: add save functionality
                System.exit(0);
            } else {
                player.moveMaybe(command);
                StdDraw.clear(StdDraw.BLACK);
                ter.renderFrame(world);
            }


        }
        // End of gameplay loop


    }


}
