package MazeGenerate;

import java.io.FileWriter;
import java.io.IOException;

public class MazeFactory {
    private final int dimension = 30;
    private final MazeGenerator mazeGenerator = new MazeGenerator(dimension);

    /**
     * MazeFactory is used for protecting the mazeGenerator code (avoid changing the mazeGenerator code)
     * And make use of the maze, entry point, and exit point generated and write into csv file
     * Therefore, this constructor will build the maze first using mazeGenerator
     * @author      mhyuenac
     */
    public MazeFactory() {
        mazeGenerator.BuildMaze();
    }

    /**
     * WriteMazeInforInCSV is used to write maze, entry point, and exit point generated and write into csv file
     * @author      mhyuenac
     */
    public void WriteMazeInformationInCSV() {
        // WriteMazeMapInCSV()
        // WriteMazeMapInCSV is used to write maze generated and write into csv file
        // START WriteMazeMapInCSV()
        try {
            FileWriter writer = new FileWriter("./map/MazeMap.csv");
            int[][] maze = mazeGenerator.maze;
            for (int row=0; row<dimension; ++row) {
                for (int col=0; col<dimension; ++col) {
                    writer.append(maze[row][col] == 0? "0": "1");
                    if (col!=dimension-1) {
                        writer.append(", ");
                    }
                }
                writer.append("\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // END WriteMazeMapInCSV()
        // WriteEntryAndExitInCSV()
        // WriteEntryAndExitInCSV is used to write entry point, and exit point generated and write into csv file
        // START WriteEntryAndExitInCSV()
        try {
            FileWriter writer = new FileWriter("./map/EntryAndExitNode.csv");
            Node Entry = mazeGenerator.Entry;
            Node Exit = mazeGenerator.Exit;
            writer.append(Integer.toString(Entry.row));
            writer.append(", ");
            writer.append(Integer.toString(Entry.col));
            writer.append("\n");
            writer.append(Integer.toString(Exit.row));
            writer.append(", ");
            writer.append(Integer.toString(Exit.col));
            writer.append("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // END WriteEntryAndExitInCSV()
    }
}
