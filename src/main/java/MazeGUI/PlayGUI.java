package MazeGUI;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;


import javax.swing.JFrame;


public class PlayGUI extends JFrame{
    public static ArrayList<ArrayList<VertexLocation>> Grid;
    public static int width = 30;
    public static int height = 30;
    private final int maze_dimension = 30;
    public static int[] Entry;
    public static int[] Exit;
    public PlayGUI(){
        // Creates the arraylist that'll contain the threads
        Grid = new ArrayList<>();

        // Creates Threads and its data and adds it to the arrayList
        int[][] maze = MazeGUI.Window.readCSV("./map/MazeMap.csv");
        int[][] nodes = MazeGUI.Window.readCSV("./map/EntryAndExitNode.csv");
        Entry = nodes[0];
        Exit = nodes[1];
        ArrayList<VertexLocation> RowOfVertex;
        for (int row=0; row<maze_dimension; ++row) {
            RowOfVertex = new ArrayList<>();
            for (int col=0; col<maze_dimension; ++col) {
                if (maze[row][col] == 0) {
                    ClearVertex clearVertex = new ClearVertex(row, col);
                    RowOfVertex.add(clearVertex);
                } else {
                    Barrier barrier = new Barrier(row, col);
                    RowOfVertex.add(barrier);
                }
            }
            Grid.add(RowOfVertex);
        }

        // Setting up the layout of the panel
        getContentPane().setLayout(new GridLayout(30,30,0,0));

        // Start & pauses all threads, then adds every square of each thread to the panel
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                getContentPane().add(Grid.get(i).get(j).square);
            }
        }

        // initial position of Tom
        Tuple Tom_position = new Tuple(Exit[1],Exit[0]);
        TomController Tom = new TomController(Tom_position);
        Grid.get(Exit[1]).get(Exit[0]).square.ChangeColor(Color.lightGray);     //Exit Point  (Tom)

        // initial position of Jerry
        Tuple Jerry_position = new Tuple(Entry[1],Entry[0]);
        JerryController Jerry = new JerryController(Jerry_position);
        Grid.get(Entry[1]).get(Entry[0]).square.ChangeColor(Color.orange);     //Entry point (Jerry)

        //Game Start
        Tom.start();
        Jerry.start();

        // Links the window to the keyboardlistenner.
        this.addKeyListener((KeyListener) new KeyboardListener());
    }
}
