package MazeGUI;

import java.awt.*;

public class ClearVertex extends VertexLocation{

    /**
     * Child of VertexLocation
     * ClearVertex is a class that represent a vertex that moving object can move along on
     * Color to white, store typeOfVertex = 0 as ClearVertex
     * @author          mhyuenac
     * @param row       The row quantity in the maze
     * @param col       The col quantity in the maze
     */
    ClearVertex(int row, int col) {
        super(row, col);
        square = new SquarePanel(Color.white);
        typeOfVertex = 0;
    }
}
