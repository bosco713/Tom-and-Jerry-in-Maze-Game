package MazeGUI;

import MazeGenerate.MazeFactory;
import pathfunction.ShortestPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Window extends JFrame{
    // private static final long serialVersionUID = -2542001418764869760L;
    public static ArrayList<ArrayList<VertexLocation>> Grid;
    private final int maze_dimension = 30;
    public static int width;
    public static int height;
    private final JPanel mazePanel;
    // private JPanel button;
    protected final JButton button;

    /**
     * Window is a class that build up the GUI window showing the respective function we have implemented
     * This constructor will set up the Grid data structure, the width, and the height of the window
     * @author      mhyuenac
     */
    public Window() {
        Grid = new ArrayList<>();
        width = maze_dimension;
        height = maze_dimension;
        mazePanel = new JPanel(new GridLayout(width, height, 0, 0));
        button = new JButton("Generate new map");
        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * readCSV is a function that can read csv file with given filePath
     * @author              mhyuenac
     * @author              hktsangad
     * @param filePath      The csv file path
     * @return              An 2D int array, which can represent the maze or Entry and Exit point
     */
    public static int[][] readCSV(String filePath) {
        int[][] map = new int[0][];

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowCount = 0;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int[] intValues = new int[values.length];

                for (int i = 0; i < values.length; i++) {
                    intValues[i] = Integer.parseInt(values[i].trim());
                }

                map = Arrays.copyOf(map, map.length + 1);
                map[rowCount++] = intValues;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    /**
     * BuildGridWithMazeMap makes use of all the maze information given in function A and build the Grid structure
     * which can used later setting up the GUI
     * @author      mhyuenac
     */
    protected void BuildGridWithMazeMap() {
        int[][] maze = readCSV("./map/MazeMap.csv");
        int[][] nodes = readCSV("./map/EntryAndExitNode.csv");
        ArrayList<VertexLocation> RowOfVertex;
        for (int row=0; row<maze_dimension; ++row) {
            RowOfVertex = new ArrayList<>();
            for (int col=0; col<maze_dimension; ++col) {
                if (maze[row][col] == 0) {
                    ClearVertex clearVertex = new ClearVertex(row, col);
                    if ((nodes[0][0] == row) && (nodes[0][1] == col)) { // Entry
                        clearVertex.square.ChangeColor(Color.red);
                    }
                    if ((nodes[1][0] == row) && (nodes[1][1] == col)) { // Exit
                        clearVertex.square.ChangeColor(Color.green);
                    }
                    RowOfVertex.add(clearVertex);
                } else {
                    Barrier barrier = new Barrier(row, col);
                    RowOfVertex.add(barrier);
                }
            }
            Grid.add(RowOfVertex);
        }
    }

    /**
     * SetUpWindowUsingGrid is used to set up the content pane using the Grid
     * @author      mhyuenac
     */
    public void SetUpMazePanelUsingGrid() {
        BuildGridWithMazeMap();
        // getContentPane().setLayout(new GridLayout(width, height, 0, 0));
        for (int row=0; row<maze_dimension; ++row) {
            for (int col=0; col<maze_dimension; ++col) {
                mazePanel.add(Grid.get(row).get(col).square);
                // getContentPane().add(Grid.get(row).get(col).square);
            }
        }
    }
    public void ShowMaze() {
        SetUpMazePanelUsingGrid();
        getContentPane().add(mazePanel, BorderLayout.CENTER);
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(100, 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MazeFactory mazeFactory = new MazeFactory();
                mazeFactory.WriteMazeInformationInCSV();
                mazePanel.removeAll();
                Grid.clear();
                SetUpMazePanelUsingGrid();
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        });
        jPanel.add(button, BorderLayout.CENTER);
        getContentPane().add(jPanel, BorderLayout.SOUTH);
    }

    /**
     * showShortestPath is used to show the shortest path between two given points
     * and displace a yellow path on top of a duplication of function A window
     * @author      hktsangad
     */
    public void showShortestPath() {
        SetUpMazePanelUsingGrid();
        getContentPane().add(mazePanel);

        int[][] map = readCSV("./map/MazeMap.csv");
        int[][] nodes = readCSV("./map/EntryAndExitNode.csv");

        int[] entryPoint = nodes[0];
        int[] exitPoint = nodes[1];

        int[][] path = ShortestPath.getPath(map, entryPoint, exitPoint);

        for (int[] ints : path) {
            VertexLocation vertex = Grid.get(ints[0]).get(ints[1]);
            vertex.square.setBackground(Color.yellow);
            vertex.square.repaint();
        }
    }
}
