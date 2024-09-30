package MazeGUI;

public class VertexLocation {
    protected int typeOfVertex = -1;
    private int row;
    private int col;
    private final int pixelLength = 10;
    private final int pixelWidth = 10;
    SquarePanel square;

    /**
     * VertexLocation is a class representing the coordinate of each square in the maze and window
     * All function here will be inheritance into child class
     * typeOfVertex will be set in the child class again --> so if it is -1, that means something wrong
     * @author          mhyuenac
     * @param row       The row quantity on the window
     * @param col       The col quantity on the window
     */
    VertexLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
