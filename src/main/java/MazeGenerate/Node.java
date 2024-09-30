package MazeGenerate;

public class Node {
    public final int row;
    public final int col;

    /**
     * MazeGenerate.Node class is used to represent the coordinate in the maze map
     * @author      mhyuenac
     * @param x     The row quantity
     * @param y     The col quantity
     */
    Node(int x, int y) {
        this.row = x;
        this.col = y;
    }

    /** EqualNode is used to check if two coordinates represented by two respective nodes are the same
     * @author              mhyuenac
     * @param anotherNode   Another node with it's coordinate
     * @return              A boolean value of whether two coordinates are equal
     */
    public boolean EqualNode(Node anotherNode) {
        return (anotherNode.row == row && anotherNode.col == col);
    }

    /** ManhanttenDistance is used to calculate the manhantten distance between two nodes
     *                   --> using the distance, can check if the distance between two nodes are too close
     * Manhantten Distance = total difference in horizontal + total difference in vertical
     * @author mhyuenac
     * @param anotherNode   Another node with it's coordinate
     * @return              An integer value of the manhantten distance between two nodes
     */
    public int ManhanttanDistance(Node anotherNode) {
        return (Math.abs(row-anotherNode.row)+Math.abs(col-anotherNode.col));
    }
}
