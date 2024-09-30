package MazeGUI;

import java.awt.Color;

public class Barrier extends VertexLocation{

    /**
     * Child of VertexLocation
     * Barrier is a class that represent a vertex that moving object cannot move along on
     * Color to darkGray, store typeOfVertex = 1 as Barrier
     * @author          mhyuenac
     * @param row       The row quantity in the maze
     * @param col       The col quantity in the maze
     */
    Barrier(int row, int col) {
        super(row, col);
        square = new SquarePanel(Color.darkGray);
        typeOfVertex = 1;
    }
}
