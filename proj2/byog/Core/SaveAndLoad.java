package byog.Core;

//Might need these?
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class SaveAndLoad {

    /** Save the current game state (with world updated and random carried over) */
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
        }  catch (FileNotFoundException e) {
            System.out.println("File not found. Have you played this game before? " +
                    "I'd try playing it. It's a lot of fun.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }


    /** Load the last-saved world state (if any exists) with player movements accounted for */
    public static void loadGame() {



    }




}
