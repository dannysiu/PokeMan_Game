package byog.Core.Trash;

//import byog.Core.Trash.WorldGeneratorOLD;
//import byog.TileEngine.TERenderer;
//import byog.TileEngine.TETile;
//import byog.TileEngine.Tileset;
//import org.junit.Test;
//
//import java.util.Random;
//
//public class TestWorldGenerator {
//
//    public TestWorldGenerator() { }
//
//
//    private TETile[][] testWorldMaker(int width, int height) {
//        TETile[][] world = new TETile[width][height];
//        for (int x = 0; x < width; x += 1) {
//            for (int y = 0; y < height; y += 1) {
//                world[x][y] = Tileset.NOTHING;
//            }
//        }
//        return world;
//    }
//
//    private TETile[][] testWorldMakerWithBorder(int width, int height) {
//        TETile[][] world = testWorldMaker(width, height);
//
//        for (int x = 0; x < width; x += 1) {
//            world[x][0] = Tileset.MOUNTAIN;
//            world[x][height - 1] = Tileset.MOUNTAIN;
//        }
//        for (int y = 0; y < height; y += 1) {
//            world[0][y] = Tileset.MOUNTAIN;
//            world[width - 1][y] = Tileset.MOUNTAIN;
//        }
//
//        return world;
//    }
//
//
//    ///////////////////Tests live down here///////////////////////
//
//    @Test
//    private void testRandomHallways() {
//        /** Tests whether randomHallways method in WorldGenerator
//         */
//        int width = 60;
//        int height = 30;
//        TestWorldGenerator tester = new TestWorldGenerator();
//        TETile[][] world = tester.testWorldMakerWithBorder(width, height);
//        TERenderer ter = new TERenderer();
//        Random random = new Random();
//        HallwayGenerator hg = new HallwayGenerator(random);
//        WorldGeneratorOLD wgo = new WorldGeneratorOLD(random);
//
//        // Where the magic happens
//        Position start = new Position(19, 15);
//        Position origin = new Position(18, 15);
//        world[origin.getX()][origin.getY()] = Tileset.MOUNTAIN;
////        WhereToNext next = new WhereToNext("right", start, true, true, world);
//
////        wgo.randomHallways(next, random, world);
//        // Where the magic ends
//
//        ter.initialize(width, height);
//        ter.renderFrame(world);
//    }
//
//
//
//
//
//
//    ////////////////////////// MAIN METHOD ///////////////////////////////
//
//    /** Visual inspection test. Change width and height declared near
// beginning to affect world. */
//    public static void main(String[] args) {
//
//        TestWorldGenerator tester = new TestWorldGenerator();
//
//        // Individual tests above. Comment out as needed
//        tester.testRandomHallways();
//
//    }
//}
