package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;


public class GameState implements java.io.Serializable {

    TETile[][] world;
    Random randomGenerator;
    Player player;
    private static final long serialVersionUID = 7488474396728367324L;


    public GameState(Random random, Player playerObject, TETile[][] initialWorldState) {
        this.randomGenerator = random;
        this.world = initialWorldState;
        this.player = playerObject;
    }


    public void gameLoop() {
        boolean gameOver = false;
        TERenderer ter = new TERenderer();

        StdDraw.enableDoubleBuffering();
        // Gameplay loop
        while (!gameOver) {
            // Drawing HUD
            updateHUD(world);
//            StdDraw.pause(20);
            ter.renderFrame(world);
            // End of drawing HUD


            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }

            char command = StdDraw.nextKeyTyped();
            if (command == 'q' || command == 'Q') {
                gameOver = true;
                // TODO: add save functionality

                System.exit(0);

            } else { //if (movementCommands.contains(command))
                player.moveMaybe(command);
                ter.renderFrame(world);
            }

        }
        // End of gameplay loop
    }

    private String tilePointer(TETile[][] world) {
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


    private void updateHUD(TETile[][] world) {
        String currTilePointed = tilePointer(world);
        StdDraw.setPenColor(Color.white);
        StdDraw.line(0, world[0].length, world.length, world[0].length);
        StdDraw.textLeft(1, world[0].length + 2, currTilePointed);

        // Place holders for future potential pikachu and jigglypuff stuff
        StdDraw.setPenColor(Color.yellow);
        StdDraw.textRight(world.length - 2, world[0].length + 2, "Pikachu status: ");
        StdDraw.setPenColor(Color.pink);
        StdDraw.textRight(world.length - 2, world[0].length + 1, "Boss-Puff status: ");
        //
        StdDraw.show();
    }

}
