package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Game implements java.io.Serializable {
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;

//    private static List<Character> movementCommands = new ArrayList<>();

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {

        TETile[][] world = initializeWorld();

        drawMenu();

        String typing = "";
        while (typing.length() < 50000000) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char input = StdDraw.nextKeyTyped();
            if (input == 'q' || input == 'Q') {
                System.exit(0);
                break;
            } else if (input == 'n' || input == 'N') {
                long seed = drawSeedWindow();
                Random random = new Random(seed);

                Game testGame = new Game();
                testGame.playNewGame(random, world);

            } else if (input == 'l' || input == 'L') {
                /** TODO: Load the previous game that was terminated
                 * and its random seed*/
                SaveAndLoad.loadGame();

            } else {
                Font font = new Font("Monaco", Font.PLAIN, 12);
                StdDraw.setFont(font);
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "Not a valid command");
                StdDraw.show();
            }
            typing += input;
        }
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

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        GameState gs;
        String playerMoves;
        Random randomGenerator;

        //Make a new world using the seed from input
        if (input.startsWith("N") || input.startsWith("n")) {
            world = initializeWorld();

            //TODO: update this to also take in direction inputs for character, quitting, loading


            //find the seed and player moves within user input
            int seedEnd = findEndOfSeedIndex(input.substring(1));
            try {
                long seed = Long.parseLong(input.substring(1, seedEnd));
                randomGenerator = new Random(seed);
                playerMoves = input.substring(seedEnd);

            } catch (java.lang.NumberFormatException e) {
                throw new java.lang.NumberFormatException("Seed may only contain numbers ");
            }

        //Or load the last game
        } else if (input.startsWith("L") || input.startsWith("l")){
            gs = SaveAndLoad.loadGame();
            randomGenerator = gs.getRandom();
            playerMoves = input.substring(1);

        } else {
            throw new RuntimeException("Input must start with 'n' or 'l'");
        }


        /** TODO: edit updateGameState to call method which returns GameState
         *  and makes players move using the string passed in
         *  Set equal to world for returning
         */

        Game testGame = new Game();
        testGame.updateGameState(randomGenerator, world, playerMoves);




        return world;
    }


    public void updateGameState(Random random, TETile[][] world, String movements) {
        TERenderer ter = new TERenderer();

        // Drawing the world map
        RoomGenerator rg = new RoomGenerator(random);
        HallwayGenerator hg = new HallwayGenerator(random);

        rg.populateRooms(world);
        hg.connectRoomsStraight(rg.getRoomList(), world);

        ter.initialize(world.length, world[0].length + 3, 0, 0);
        ter.renderFrame(world);
        // Finished drawing world map

        // Adding players to world map
        Player player = new Player(random, ter, world);
        // Finished adding players

        // TODO: Activate game-loading with player movements rendered
        //pass in string movements!!
//        GameState readyToLoad = new GameState(random, player, ter, world);

    }


    public static TETile[][] initializeWorld() {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }


    public int findEndOfSeedIndex(String input) {
        int endOfSeed;
        if (input.contains("s")) {
            endOfSeed = input.indexOf("s");
        } else if (input.contains("S")) {
            endOfSeed = input.indexOf("S");
        } else {
            throw new RuntimeException("Missing 's'. Don't know where the seed ends.");
        }

        return endOfSeed;
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


    // @source Inspired by Josh Hug's Memory Game solution and displaying the user's typing
    public long drawSeedWindow() {

        //draw window with instructions
        StdDraw.setCanvasSize(WIDTH * 8, HEIGHT* 8);

        String seed = "";
        updateSeedWindow(seed);

        //simulate player typing the seed in real time
        while (!seed.endsWith("s") && !seed.endsWith("S")) {
            if (StdDraw.hasNextKeyTyped()) {
                char next = StdDraw.nextKeyTyped();
                seed += next;
                updateSeedWindow(seed);
            }
        }

        return Long.parseLong(seed.substring(0,seed.length()-2));

    }


    public void updateSeedWindow(String s) {

        StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);

        Font font1 = new Font("Monaco", Font.PLAIN, 18);
        Font font2 = new Font("Monaco", Font.PLAIN, 18);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);

        StdDraw.setFont(font1);
        StdDraw.text(WIDTH / 2, 3 * HEIGHT / 4, "Type in your seed:");


        //show the text that user types in
        StdDraw.setFont(font2);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, s);

        StdDraw.show();

    }


    public static void playNewGame(Random random, TETile[][] world) {

        TERenderer ter = new TERenderer();

        // Drawing the world map
        RoomGenerator rg = new RoomGenerator(random);
        HallwayGenerator hg = new HallwayGenerator(random);

        rg.populateRooms(world);
        hg.connectRoomsStraight(rg.getRoomList(), world);

        ter.initialize(world.length, world[0].length + 3, 0, 0);
        ter.renderFrame(world);
        // Finished drawing world map

        // Adding players to world map
        Player player = new Player(random, ter, world);
        // Finished adding players

        // Activate game-play loop
        GameState readyToPlay = new GameState(random, player, ter, world);
        readyToPlay.gameLoop();

    }

    /** Overloads above method to instead return the TETile[][] world after setup */
    public static GameState playNewGame(Random random, TETile[][] world, String noLoadCode) {

        TERenderer ter = new TERenderer();

        // Drawing the world map
        RoomGenerator rg = new RoomGenerator(random);
        HallwayGenerator hg = new HallwayGenerator(random);

        rg.populateRooms(world);
        hg.connectRoomsStraight(rg.getRoomList(), world);

        ter.initialize(world.length, world[0].length + 3, 0, 0);
        ter.renderFrame(world);
        // Finished drawing world map

        // Adding players to world map
        Player player = new Player(random, ter, world);
        // Finished adding players

        // Activate game-play loop
        GameState readyToPlay = new GameState(random, player, ter, world);
        return readyToPlay;
    }





    /////////////////////// FOR TESTING /////////////////////////////////////
    public static void main(String[] args) {

        //Draw the screen
        int worldWidth = 80;
        int worldHeight = 40;

        TETile[][] world = new TETile[worldWidth][worldHeight];
        for (int x = 0; x < worldWidth; x += 1) {
            for (int y = 0; y < worldHeight; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

//        // Pikachu movements
//        movementCommands.add('a');
//        movementCommands.add('A');
//        movementCommands.add('s');
//        movementCommands.add('S');
//        movementCommands.add('d');
//        movementCommands.add('D');
//        movementCommands.add('w');
//        movementCommands.add('W');
//        // Boss-puff movements
//        movementCommands.add('j');
//        movementCommands.add('J');
//        movementCommands.add('k');
//        movementCommands.add('K');
//        movementCommands.add('l');
//        movementCommands.add('L');
//        movementCommands.add('i');
//        movementCommands.add('I');

        // Seed goes here
        Random random = new Random();

        Game testGame = new Game();
        testGame.playNewGame(random, world);


    }

}
