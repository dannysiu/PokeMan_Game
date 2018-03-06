package byog.Core;

import byog.TileEngine.TETile;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;


public class SaveAndLoad {

    /**
     * Save the current game state (with world updated and random carried over)
     * @source the SaveDemo provided to us in the skeleton.
     */
    public static void saveGame(GameState currGame) {
        File file = new File("./lastGame.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(currGame);
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }


    /**
     * Load the last-saved world state (if any exists) with player movements accounted for
     */
    public static GameState loadGame() {

        File file = new File("./lastGame.txt");
        if (file.exists()) {
            try {
                FileInputStream fs = new FileInputStream(file);
                ObjectInputStream os = new ObjectInputStream(fs);
                GameState currGame = (GameState) os.readObject();
                os.close();
                return currGame;
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Have you played this game before? " +
                        "I'd try playing it. It's a lot of fun.");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }

        }
        // If no file to load, will instead instantiate a new game and provide it
        Random noLoadRandom = new Random();
        TETile[][] noLoadWorld = Game.initializeWorld();
        GameState newGame = Game.playNewGame(noLoadRandom, noLoadWorld);
        return newGame;
    }
}
