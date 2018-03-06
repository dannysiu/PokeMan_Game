package byog.TileEngine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    public static final TETile PLAYER = new TETile('@', Color.white, Color.black, "player");
//    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
//            "wall");
//    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black,
//            "floor");
    public static final TETile NOTHING = new TETile('█', new Color(89, 192, 128),
        new Color(89, 192, 128), "nothing");
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "grass");
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "water");
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower");
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "locked door");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door");
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "sand");
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree");

    // Custom content!
//    public static final TETile PIKACHU = new TETile('P', Color.yellow, Color.black,
//            "Pikachu");
//    public static final TETile JIGGLYPUFF = new TETile('J', Color.pink, Color.black,
//            "It's Boss Puff!");
    public static final TETile TAXES = new TETile('T', Color.white, Color.black,
            "taxes...");
    public static final TETile BRIEFCASE = new TETile('B', Color.darkGray, Color.black,
            "My briefcase!");

    public static final TETile PIKACHU = new TETile('P', Color.yellow, Color.black,
            "Pikachu", "C:\\Users\\chat2\\Documents\\Spring 2018\\CS 61B\\sp18-proj2-axr-bfl\\" +
            "proj2\\byog\\Core\\images\\pika_sprite_small.png");
    public static final TETile JIGGLYPUFF = new TETile('J', Color.pink, Color.black,
            "It's Boss Puff!", "C:\\Users\\chat2\\Documents\\Spring 2018\\CS 61B\\sp18-proj2-axr-bfl\\" +
            "proj2\\byog\\Core\\images\\boss_puff.png");
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black,
            "floor", "C:\\Users\\chat2\\Documents\\Spring 2018\\CS 61B\\sp18-proj2-axr-bfl\\" +
            "proj2\\byog\\Core\\images\\grass.png");
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall", "C:\\Users\\chat2\\Documents\\Spring 2018\\CS 61B\\sp18-proj2-axr-bfl\\" +
            "proj2\\byog\\Core\\images\\forest.png");

//    TETile(char character, Color textColor, Color backgroundColor, String description,
//           String filepath)
}


