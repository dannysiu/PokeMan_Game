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
                GameState prevGame = (GameState) os.readObject();
                os.close();
                return prevGame;
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Have you played this game before? "
                        + "I'd try playing it. It's a lot of fun.");
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
        System.exit(0); // For clarification: do not start a game if no game to load

        // For sake of compiler, so that it can return something. Code should never reach here
        Random noLoadRandom = new Random();
        TETile[][] noLoadWorld = Game.initializeWorld();
        GameState falseGame = Game.playNewGame(noLoadRandom, noLoadWorld);
        return falseGame;
    }
}
