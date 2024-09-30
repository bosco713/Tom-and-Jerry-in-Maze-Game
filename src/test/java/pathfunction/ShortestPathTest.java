package pathfunction;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

/**
 * A test class for the {@link ShortestPath} class.
 *
 * @author  hktsangad
 */
public class ShortestPathTest {

    /**
     * Tests the {@link ShortestPath#initializeParentArray(int[][])} method.
     *
     * @author  hktsangad
     */
    @Test
    void initializeParentArrayTest() {
        int[][] map = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        int[][][] expected = {
                {{-1, -1}, {-1, -1}, {-1, -1}},
                {{-1, -1}, {-1, -1}, {-1, -1}},
                {{-1, -1}, {-1, -1}, {-1, -1}}
        };

        assertArrayEquals(expected, ShortestPath.initializeParentArray(map));   //target function
    }

    /**
     * Tests the {@link ShortestPath#toArray(LinkedList)} method.
     *
     * @author  hktsangad
     */
    @Test
    void toArrayTest() {
        LinkedList<int[]> list = new LinkedList<>();
        list.add(new int[]{0, 0});
        list.add(new int[]{1, 1});

        int[][] expected = {{0, 0}, {1, 1}};

        assertArrayEquals(expected, ShortestPath.toArray(list));    //target function
    }

    /**
     * Tests the {@link ShortestPath#isValidPosition(int, int, int[][])} method.
     *
     * @author  hktsangad
     */
    @Test
    void isValidPositionTest() {
        int[][] map = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        int row1 = 0;
        int col1 = 0;

        int row2 = map.length-1;
        int col2 = map[0].length-1;

        int row3 = -1;
        int col3 = -1;

        int row4 = map.length;
        int col4 = map[0].length;

        assertTrue(ShortestPath.isValidPosition(row1, col1, map));  //target function
        assertTrue(ShortestPath.isValidPosition(row2, col2, map));  //target function
        assertFalse(ShortestPath.isValidPosition(row3, col3, map)); //target function
        assertFalse(ShortestPath.isValidPosition(row4, col4, map)); //target function
    }

    /**
     * Tests the {@link ShortestPath#isUnvisitedCell(int, int, int[][], int[][])} method.
     *
     * @author  hktsangad
     */
    @Test
    void isUnvisitedCellTest() {
        int[][] visited = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 2}
        };

        int[][] map = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        assertTrue(ShortestPath.isUnvisitedCell(0, 0, visited, map));   //target function
        assertFalse(ShortestPath.isUnvisitedCell(1, 1, visited, map));  //target function
        assertFalse(ShortestPath.isUnvisitedCell(2, 2, visited, map));  //target function
    }

    /**
     * Tests the {@link ShortestPath#bfs(int[][], int[])} method.
     *
     * @author  hktsangad
     */
    @Test
    void bfsTest() {
        int[][] map1 = {
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}
        };
        int[][] map2 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int[][] map3 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        int[] entryPoint = {0, 0};

        int[][][] expected1 = {
                {{-1, -1}, {0, 0}, {0, 1}},
                {{0, 0}, {-1, -1}, {-1, -1}},
                {{1, 0}, {2, 0}, {2, 1}}
        };
        int[][][] expected2 = {
                {{-1, -1}, {0, 0}, {0, 1}},
                {{0, 0}, {-1, -1}, {0, 2}},
                {{1, 0}, {2, 0}, {1, 2}}
        };
        int[][][] expected3 = {
                {{-1, -1}, {0, 0}, {0, 1}},
                {{0, 0}, {0, 1}, {0, 2}},
                {{1, 0}, {1, 1}, {1, 2}}
        };

        assertArrayEquals(expected1, ShortestPath.bfs(map1, entryPoint));   //target function
        assertArrayEquals(expected2, ShortestPath.bfs(map2, entryPoint));   //target function
        assertArrayEquals(expected3, ShortestPath.bfs(map3, entryPoint));   //target function
    }

    /**
     * Tests the {@link ShortestPath#constructPath(int[], int[], int[][][])} method.
     *
     * @author  hktsangad
     */
    @Test
    void constructPathTest() {
        int[][][] parent = {
                {{-1, -1}, {0, 0}, {0, 1}},
                {{0, 0}, {0, 1}, {0, 2}},
                {{1, 0}, {1, 1}, {1, 2}}
        };

        int[] entryPoint = {0, 0};

        int[] exitPoint1 = {0, 0};
        int[] exitPoint2 = {1, 1};
        int[] exitPoint3 = {2, 2};

        int[][] expected1 = {{0, 0}};
        int[][] expected2 = {{0, 0}, {0, 1}, {1, 1}};
        int[][] expected3 = {{0, 0}, {0, 1}, {0, 2}, {1, 2}, {2, 2}};

        assertArrayEquals(expected1, ShortestPath.constructPath(entryPoint, exitPoint1, parent));   //target function
        assertArrayEquals(expected2, ShortestPath.constructPath(entryPoint, exitPoint2, parent));   //target function
        assertArrayEquals(expected3, ShortestPath.constructPath(entryPoint, exitPoint3, parent));   //target function
    }

    /**
     * Tests the {@link ShortestPath#getPath(int[][], int[], int[])} method.
     *
     * @author  hktsangad
     */
    @Test
    void getPathTest() {
        int[][] map = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0}
        };
        int[] entryPoint = {0, 0};
        int[] exitPoint1 = {0, 3};
        int[] exitPoint2 = {1, 3};
        int[] exitPoint3 = {2, 3};
        int[] exitPoint4 = {3, 3};

        int[][] expected1 = {{0, 0}, {0, 1}, {0, 2}, {0, 3}};
        int[][] expected2 = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {1, 3}};
        int[][] expected3 = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {1, 3}, {2, 3}};
        int[][] expected4 = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {1, 3}, {2, 3}, {3, 3}};

        assertArrayEquals(expected1, ShortestPath.getPath(map, entryPoint, exitPoint1));    //target function
        assertArrayEquals(expected2, ShortestPath.getPath(map, entryPoint, exitPoint2));    //target function
        assertArrayEquals(expected3, ShortestPath.getPath(map, entryPoint, exitPoint3));    //target function
        assertArrayEquals(expected4, ShortestPath.getPath(map, entryPoint, exitPoint4));    //target function
    }

    /**
     * Tests the {@link ShortestPath#generatePathInCSV(int[][], String)} method.
     *
     * @author  hktsangad
     */
    @Test
    void generatePathInCSVTest() {
        int[][] path = {
                {12, 0},
                {12, 1},
                {12, 2},
                {13, 2},
                {13, 3},
                {13, 4},
                {14, 4},
                {14, 5},
                {14, 6},
                {14, 7},
                {14, 8},
                {13, 8},
                {12, 8},
                {12, 9},
                {12, 10},
                {11, 10},
                {10, 10},
                {10, 11},
                {10, 12},
                {9, 12},
                {9, 13},
                {8, 13},
                {7, 13},
                {7, 14},
                {7, 15},
                {6, 15},
                {5, 15},
                {4, 15},
                {4, 16},
                {4, 17},
                {4, 18},
                {4, 19},
                {4, 20},
                {4, 21},
                {5, 21},
                {6, 21},
                {6, 22},
                {6, 23},
                {6, 24},
                {5, 24},
                {5, 25},
                {5, 26},
                {4, 26},
                {3, 26},
                {2, 26},
                {1, 26},
                {1, 27},
                {1, 28},
                {1, 29}
        };

        String filePath = "./src/test/map/TestMazePath.csv";

        try {
            ShortestPath.generatePathInCSV(path, filePath); //target function
        } catch (RuntimeException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
