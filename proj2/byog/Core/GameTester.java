package byog.Core;

import byog.TileEngine.TETile;

public class GameTester {

    public static void main(String[] args) {
        if (args.length == 1) {
            Game game = new Game();
            game.playWithInputString(args[0]);
            TETile[][] worldState = game.playWithInputString(args[0]);
            System.out.println(TETile.toString(worldState));
        }
    }
}
