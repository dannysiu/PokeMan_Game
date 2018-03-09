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
//    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "water");
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower");
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "locked door");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door");
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "sand");
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree");

    // Custom content!
//    public static final TETile TAXES = new TETile('T', Color.white, Color.black,
//            "taxes...");
//    public static final TETile BRIEFCASE = new TETile('B', Color.darkGray, Color.black,
//            "My briefcase!");


    static String currDir = System.getProperty("user.dir");
    static String imageDir = currDir + "\\byog\\Core\\images\\";

    //Game Characters
    public static final TETile PIKACHU = new TETile('P', Color.yellow, Color.black,
            "Pikachu", imageDir + "pika_sprite_small.png");
    public static final TETile JIGGLYPUFF = new TETile('J', Color.pink, Color.black,
            "It's Boss Puff!", imageDir + "boss_puff.png");
    public static final TETile BULBASAUR = new TETile('B', Color.green, Color.gray,
            "Bulbasaur", imageDir + "bulbasaur.png");
    public static final TETile CHARMANDER = new TETile('C', Color.red, Color.black,
            "Charmander", imageDir + "charmander.png");
    public static final TETile SQUIRTLE = new TETile('S', Color.blue, Color.gray,
            "Squirtle", imageDir + "squirtle.png");
    public static final TETile VULPIX = new TETile('V', new Color(192, 95, 8), Color.white,
            "Vulpix", imageDir + "vulpix.png");
    public static final TETile CLEFAIRY = new TETile('C', Color.pink, Color.white,
            "Clefairy", imageDir + "clefairy.png");


    //Pokemon World
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black,
            "floor", imageDir + "grass2.png");
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall", imageDir + "forest.png");
//    public static final TETile WATER = new TETile('█', new Color(89, 192, 128),
//            new Color(89, 192, 128), "nothing");


    //Items and Traps
    public static final TETile RAZZBERRY = new TETile('F', new Color(216, 128, 128), Color.darkGray,
            "Razz-berry", imageDir + "razzberry.png");
    public static final TETile PINAPBERRY = new TETile('P', Color.ORANGE, Color.darkGray,
            "Pinap-berry", imageDir + "pinapberry.png");

    public static final TETile POKEBALL = new TETile('B', new Color(216, 20, 15), Color.darkGray,
            "Pokeball Trap!", imageDir + "pokeball.png");

    //Obstacles
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black,
            "water", imageDir + "water.png");


//    TETile(char character, Color textColor, Color backgroundColor, String description,
//           String filepath)
}


