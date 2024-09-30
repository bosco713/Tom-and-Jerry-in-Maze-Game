package MazeGenerate;

import MazeGUI.Window;

public class MazeMapGUI {
    private Window window;

    /**
     * MazeMapGUI is used to make the GUI which display the maze map generated
     * The constructor create a new window for this part
     * @author      mhyuenac
     */
    public MazeMapGUI() {
        window = new Window();
    }

    /**
     * BuildMazeMapGUI is used to make the GUI:
     * 1. Set up the window according to the maze map generated
     * 2. Set title, and size
     * @author      mhyuenac
     */
    public void BuildMazeMapGUI() {
        window.ShowMaze();
        window.setTitle("MazeMap");
        window.setSize(300,350);
        window.setVisible(true);
    }
}
