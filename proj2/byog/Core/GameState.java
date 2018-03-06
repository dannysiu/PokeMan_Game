package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Font;
import java.awt.Color;
import java.util.Random;


public class GameState implements java.io.Serializable {

    private TETile[][] world;
    private Random randomGenerator;
    private Player player;
//    private TERenderer renderEngine;
    private static final long serialVersionUID = 7488474396728367324L;


    public GameState(Random random, Player playerObject, TERenderer ter,
                     TETile[][] initialWorldState) {
        this.randomGenerator = random;
        this.world = initialWorldState;
        this.player = playerObject;
//        this.renderEngine = ter;
//        renderEngine.initialize(world.length, world[0].length + 3, 0, 0);
    }

    /** Version without renderer for playing with input string */
    public GameState(Random random, Player playerObject, TETile[][] initialWorldState) {
        this.randomGenerator = random;
        this.world = initialWorldState;
        this.player = playerObject;
    }


    /** Method used to start the gamplay loop for keyboard games.
     *  Updates HUD and player movements as necessary, drawing world state for each change.
     */
    public void gameLoop() {
        boolean gameOver = false;
        boolean quitPrimed = false;
        TERenderer ter = new TERenderer();
        ter.initialize(world.length, world[0].length + 3, 0, 0);


        StdDraw.enableDoubleBuffering();
        // Gameplay loop
        while (!gameOver) {
            // Drawing HUD
            updateHUD();
            ter.renderFrame(world);
            // End of drawing HUD

            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }

            char command = StdDraw.nextKeyTyped();
            if (command == ':') {
                quitPrimed = true;
            }
            if (quitPrimed && command == 'q' || command == 'Q') {
                gameOver = true;
                SaveAndLoad.saveGame(this);
                endGameMenu();

            } else { //if (movementCommands.contains(command))
                quitPrimed = false;
                player.moveMaybe(command);
                ter.renderFrame(world);
            }
        }
        // End of gameplay loop
    }


    /** Method used to process and execute string commands from a string input game */
    public TETile[][] gamePlayWithString(String inputString) {

        boolean quitPrimed = false;

        for (char command : inputString.toCharArray()) { // Change this to account for the string.
            // Maybe a for loop?

            if (command == ':') {
                quitPrimed = true;
            }
            if (quitPrimed && command == 'q' || command == 'Q') {
                SaveAndLoad.saveGame(this);
                return world;

            } else { //if (movementCommands.contains(command))
                quitPrimed = false;
                player.moveMaybe(command);
            }
        }
        return world;
    }


    //////////////////// Helper methods below ///////////////////////

    /** For locating position of mouse and updating HUD accordingly */
    private String tilePointer() {
        String answer;

        int mouseX = Math.toIntExact(Math.round(StdDraw.mouseX() - 0.5));
        int mouseY = Math.toIntExact(Math.round(StdDraw.mouseY() - 0.5));

        if (mouseX > world.length - 1 || mouseY > world[0].length - 1) {
            answer = " ";
        } else {
            TETile spot = world[mouseX][mouseY];
            answer = spot.description();
        }
        return answer;
    }


    /** For updating the HUD at the top of the draw window */
    private void updateHUD() {
        String currTilePointed = tilePointer();
        StdDraw.setPenColor(Color.white);
        StdDraw.line(0, world[0].length, world.length, world[0].length);
        StdDraw.textLeft(1, world[0].length + 2, currTilePointed);

        // Place holders for future potential pikachu and jigglypuff stuff
        StdDraw.setPenColor(Color.yellow);
        StdDraw.textLeft(7 * world.length / 10, world[0].length + 2,
                "Pika-Man status: slacking off...");
        StdDraw.setPenColor(Color.pink);
        StdDraw.textLeft(7 * world.length / 10, world[0].length + 1,
                "Boss-Puff status: angry!");
        //
        StdDraw.show();
    }


    private void endGameMenu() {
        int worldWidth = Game.WIDTH;
        int worldHeight = Game.HEIGHT;

        // Start drawing menu
        StdDraw.setCanvasSize(worldWidth * 16, worldHeight * 16);
        StdDraw.clear(StdDraw.PINK);

        Font font1 = new Font("Helvetica", Font.BOLD, 45);
        Font font2 = new Font("Monaco", Font.BOLD, 30);

        StdDraw.setXscale(0, worldWidth);
        StdDraw.setYscale(0, worldHeight);

        StdDraw.setFont(font1);
        StdDraw.text(worldWidth / 2, 3 * worldHeight / 4, "Game Over!");
        StdDraw.setFont(font2);
        StdDraw.textLeft(worldWidth / 5, worldHeight / 3, "For Main Menu:  M");
        StdDraw.textRight(4 * worldWidth / 5, worldHeight / 3, "To Quit Game:  Q");

        StdDraw.show();
        // End of drawing menu

        // Wait for keyboard input
        Game game = new Game();
        while (true) {  //only exit the program
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char input = StdDraw.nextKeyTyped();
            if (input == 'q' || input == 'Q') {
                System.exit(0);
                break;
            } else if (input == 'm' || input == 'M') {
                game.playWithKeyboard();
            }
        }
        // End of keyboard input
    }

    public TETile[][] getWorld() {
        return world;
    }
    public Random getRandom() {
        return randomGenerator;
    }
}
