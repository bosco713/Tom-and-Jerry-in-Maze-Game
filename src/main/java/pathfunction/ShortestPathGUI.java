package pathfunction;

import MazeGUI.Window;

/**
 * A class that provides a simple interface for displaying the shortest path.
 *
 * @author  hktsangad
 */
public class ShortestPathGUI {

    /**
     * Constructs and displays the GUI for visualizing the shortest path on a maze map.
     *
     * @author  hktsangad
     */
    public static void constructShortestPathGUI() {
        Window window = new Window();
        window.showShortestPath();
        window.setTitle("ShortestPath");
        window.setSize(300,300);
        window.setVisible(true);
    }
}
