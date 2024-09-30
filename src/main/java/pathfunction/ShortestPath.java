package pathfunction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A class that provides functionality for finding the shortest path in a 2D map using Breadth-First Search (BFS).
 * @author  hktsangad
 */
public class ShortestPath {

    private static final int[] NO_PARENT = {-1, -1};
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    /**
     * Initializes a 3D parent array for tracking the parent coordinates during BFS traversal.
     *
     * @author  hktsangad
     * @param map The 2D map representing the environment.
     * @return A 3D array initialized with {@code NO_PARENT} values.
     */
    public static int[][][] initializeParentArray(int[][] map) {
        int row = map.length;
        int col = map[0].length;
        int[][][] parent = new int[row][col][];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                parent[i][j] = NO_PARENT;
            }
        }
        return parent;
    }

    /**
     * Converts a linked list of coordinates to a 2D array.
     *
     * @author  hktsangad
     * @param list The linked list of coordinates.
     * @return A 2D array representation of the coordinates.
     */
    public static int[][] toArray(LinkedList<int[]> list) {
        int[][] array = new int[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * Checks if the given position is valid within the map boundaries.
     *
     * @author  hktsangad
     * @param row The row index.
     * @param col The column index.
     * @param map The 2D map representing the environment.
     * @return {@code true} if the position is valid, {@code false} otherwise.
     */
    public static boolean isValidPosition(int row, int col, int[][] map) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
    }

    /**
     * Checks if the cell at the given position is unvisited in both the visited and map arrays.
     *
     * @author  hktsangad
     * @param row     The row index.
     * @param col     The column index.
     * @param visited The 2D array tracking visited cells.
     * @param map     The 2D map representing the environment.
     * @return {@code true} if the cell is unvisited, {@code false} otherwise.
     */
    public static boolean isUnvisitedCell(int row, int col, int[][] visited, int[][] map) {
        int UNVISITED = 0;
        return visited[row][col] == UNVISITED && map[row][col] == UNVISITED;
    }

    /**
     * Performs Breadth-First Search (BFS) traversal on the map to find the shortest path.
     *
     * @author  hktsangad
     * @param map         The 2D map representing the environment.
     * @param entryPoint  The starting point for the search.
     * @return A 3D array representing the parent coordinates for each cell.
     */
    public static int[][][] bfs(int[][] map, int[] entryPoint) {
        int[][] visited = new int[map.length][map[0].length];
        int VISITED = 2;
        visited[entryPoint[0]][entryPoint[1]] = VISITED;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entryPoint);

        int[][][] parent = initializeParentArray(map);
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (isValidPosition(newRow, newCol, map) && isUnvisitedCell(newRow, newCol, visited, map)) {
                    visited[newRow][newCol] = VISITED;
                    parent[newRow][newCol] = current;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
            visited[row][col] = VISITED;
        }

        return parent;
    }

    /**
     * Constructs the path from the entry point to the exit point using the parent array.
     *
     * @author  hktsangad
     * @param entrypoint The starting point of the path.
     * @param exitPoint  The ending point of the path.
     * @param parent     The 3D array representing the parent coordinates for each cell.
     * @return A 2D array representing the shortest path.
     */
    public static int[][] constructPath(int[] entrypoint, int[] exitPoint, int[][][] parent) {
        LinkedList<int[]> path = new LinkedList<>();
        path.add(exitPoint);
        if (!Arrays.equals(entrypoint, exitPoint)) {
            int[] prev = parent[exitPoint[0]][exitPoint[1]];
            while (!Arrays.equals(prev, NO_PARENT)) {
                path.addFirst(prev);
                prev = parent[prev[0]][prev[1]];
            }
        }
        return toArray(path);
    }

    /**
     * Appends a 2D array of path coordinates to a CSV file.
     *
     * @author  hktsangad
     * @param path      The 2D array representing the path coordinates.
     * @param filePath  The path to the CSV file.
     * @throws RuntimeException If an I/O error occurs during file writing or path numbering.
     */
    public static void generatePathInCSV(int[][] path, String filePath) {
        File file = new File(filePath);

        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < path.length; i++) {
                writer.append("SP");
                writer.append(", ");
                writer.append(String.valueOf(1));
                writer.append(", ");
                writer.append(String.valueOf(i + 1));
                writer.append(", ");
                writer.append(String.valueOf(path[i][0]));
                writer.append(", ");
                writer.append(String.valueOf(path[i][1]));
                writer.append(";");

                if (i < path.length - 1) {
                    writer.append(System.lineSeparator());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds the shortest path from the entry point to the exit point on the given map and print the path to a CSV file.
     *
     * @author  hktsangad
     * @param map        The 2D map representing the environment.
     * @param entryPoint The starting point of the path.
     * @param exitPoint  The ending point of the path.
     * @return A 2D array representing the shortest path.
     */
    public static int[][] getPath(int[][] map, int[] entryPoint, int[] exitPoint) {
        int[][][] parent = bfs(map, entryPoint);
        int[][] path = constructPath(entryPoint, exitPoint, parent);

        generatePathInCSV(path, "./map/MazePath.csv");
        return path;
    }
}
