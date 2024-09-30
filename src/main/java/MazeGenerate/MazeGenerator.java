package MazeGenerate;

import java.util.Random;

public class MazeGenerator {
    private final int ClearVertex;
    private final int Barrier;
    private final Random rand = new Random();
    protected int repeatedRow;   // fix the last row no vertices problem
    protected int repeatedCol;   // fix the last col no vertices problem
    protected final int[][] maze;
    private final int dimension;
    private final int randSize = 100;
    protected Node Entry;
    protected Node Exit;
    private Node[] ShortestPath;
    private MinimumSpanningTree MST;

    /**
     * MazeGenerate.MazeGenerator is a class that make use of MST generated to contruct the path of the maze
     * and then remaining vertices will be Barrier
     * @author          mhyuenac
     * @param dim       The length of the maze map
     */
    MazeGenerator(int dim) {
        ClearVertex = 0;
        Barrier = 1;
        maze = new int[dim][dim];
        dimension = dim;
        repeatedRow = 2 * (rand.nextInt((dimension - 2) / 2) + 1);
        repeatedCol = 2 * (rand.nextInt((dimension - 2) / 2) + 1);
        // selectEntryWithExitPoint()
        // selectEntryWithExitPoint is used to random choose the Entry and Exit Nodes
        // CAUTION: Need to call this function again for unit testing as Entry and Exit Nodes has already generated in the constructor of this class
        // Call this again to generate on using the assigned random seed
        // START selectEntryWithExitPoint()
        int randNum = rand.nextInt(4);
        switch (randNum) {
            case (0) -> {
                Entry = new Node(0, 0);
                Exit = new Node(29, 29);
            }
            case (1) -> {
                Entry = new Node(0, 29);
                Exit = new Node(29, 0);
            }
            case (2) -> {
                Entry = new Node(29, 0);
                Exit = new Node(0, 29);
            }
            case (3) -> {
                Entry = new Node(29, 29);
                Exit = new Node(0, 0);
            }
        }
        // END selectEntryWithExitPoint()
        MST = new MinimumSpanningTree(dimension / 2, randSize, Entry);
    }

    /**
     * SetRandSeed is used to set the random seed of all the random class under MazeGenerator: this, and MinimumSpanningTree
     * @author          mhyuenac
     * @param seed      The seed to assign in the random classes
     */
    public void SetRandSeed(int seed) { // for unit-testing: need to set up a rand seed and then regenerate all the random objects
        rand.setSeed(seed);
        repeatedRow = 2 * (rand.nextInt((dimension - 2) / 2) + 1);
        repeatedCol = 2 * (rand.nextInt((dimension - 2) / 2) + 1);
        // selectEntryWithExitPoint()
        // selectEntryWithExitPoint is used to random choose the Entry and Exit Nodes
        // START selectEntryWithExitPoint()
        int randNum = rand.nextInt(4);
        switch (randNum) {
            case (0) -> {
                Entry = new Node(0, 0);
                Exit = new Node(29, 29);
            }
            case (1) -> {
                Entry = new Node(0, 29);
                Exit = new Node(29, 0);
            }
            case (2) -> {
                Entry = new Node(29, 0);
                Exit = new Node(0, 29);
            }
            case (3) -> {
                Entry = new Node(29, 29);
                Exit = new Node(0, 0);
            }
        }
        // END selectEntryWithExitPoint()
        MST = new MinimumSpanningTree(dimension / 2, randSize, Entry);
        MST.SetRandSeed(seed);
    }

    /**
     * BuildMaze makes use of a special initialization and then the MST(size = dimension/2) to build a maze
     * connectThePath will connect the MST inside the maze and call another function to connect the second path with checking
     * @author      mhyuenac
     */
    public void BuildMaze() {
        // initialize()
        // initialize is used to initialize the maze map with alternating 1(Barrier) and 0 (ClearVertex)
        // (make sure a Barrier will be separating each ClearVertex (this is how a maze looks like))
        // (the coordinate in maze will be double of MST) (MST[0][0] = maze[0][0], MST[0][1] = maze[0][2], maze[0][1] is a Barrier)
        // There will be 15 ClearVertex and 14 Barrier if maze[0][0] = ClearVertex
        // and the MST will be generated to show all the connection between these 15x15 vertices
        // This only generate a 29x29 maze map --> repeat one layer of ClearVertex
        // one more layer of ClearVertex will be generated at the repeatedRow and repeatedCol
        // START initialization
        for (int row=0; row<dimension; ++row) {
            for (int col=0; col<dimension; ++col) {
                if ((row <= repeatedRow) && (col <= repeatedCol)) {
                    if ((row % 2 == 0) && (col % 2 == 0)) {
                        maze[row][col] = ClearVertex;
                    } else {
                        maze[row][col] = Barrier;
                    }
                } else if ((row <= repeatedRow) && (col > repeatedCol)) {
                    if ((row % 2 == 0) && (col % 2 == 1)) {
                        maze[row][col] = ClearVertex;
                    } else {
                        maze[row][col] = Barrier;
                    }
                } else if ((row > repeatedRow) && (col <= repeatedCol)) {
                    if ((row % 2 == 1) && (col % 2 == 0)) {
                        maze[row][col] = ClearVertex;
                    } else {
                        maze[row][col] = Barrier;
                    }
                } else {
                    if ((row % 2 == 1) && (col % 2 == 1)) {
                        maze[row][col] = ClearVertex;
                    } else {
                        maze[row][col] = Barrier;
                    }
                }
            }
        }
        // END initialization
        MST.PrimsAlgorithm();
        // connectThePath()
        // connectThePath makes use of the ParentArray(show which parent node connects to the target node) MST generated to connect the ClearVertex to paths
        // Basically it breaks the Barrier between NodeA and NodeB if there is a connection between NodeA and NodeB
        // START connectThePath
        Node[][] parentArray = MST.GetParentArray();
        for (int row=0; row<dimension/2; ++row) {
            for (int col=0; col<dimension/2; ++col) {
                if ((row != Entry.row/2) || (col != Entry.col/2)) { // no parent for Entry
                    Node parentNode = parentArray[row][col];
                    int mazeRow = vertices2MazeRow(row);
                    int mazeCol = vertices2MazeCol(col);
                    if (parentNode.row == row) { // horizontal neighbor
                        if (parentNode.col < col) {
                            maze[mazeRow][mazeCol-1] = ClearVertex;
                        } else {
                            if (2*col == repeatedCol) {
                                maze[mazeRow][mazeCol+2] = ClearVertex;
                            } else {
                                maze[mazeRow][mazeCol + 1] = ClearVertex;
                            }
                        }
                    } else if (parentNode.col == col) { // vertical neighbor
                        if (parentNode.row < row) {
                            maze[mazeRow-1][mazeCol] = ClearVertex;
                        } else {
                            if (2*row == repeatedRow) {
                                maze[mazeRow+2][mazeCol] = ClearVertex;
                            } else {
                                maze[mazeRow + 1][mazeCol] = ClearVertex;
                            }
                        }
                    }
                }
            }
        }
        // END connectThePath
        // locateShortestPath()
        // locateShortestPath is used to retrieve the shortest path using the parent array
        // START locateShortestPath()
        ShortestPath = new Node[1];
        ShortestPath[0] = new Node (Exit.row/2, Exit.col/2);
        // Node[][] parentArray = MST.GetParentArray();
        int row = Exit.row/2;
        int col = Exit.col/2;
        while ((row != Entry.row/2) && (col != Entry.col/2)) {
            Node[] tempArray = new Node[ShortestPath.length+1];
            System.arraycopy(ShortestPath, 0, tempArray, 0, ShortestPath.length);
            tempArray[ShortestPath.length] = parentArray[row][col];
            row = tempArray[ShortestPath.length].row;
            col = tempArray[ShortestPath.length].col;
            ShortestPath = tempArray;
        }
        // END locationShortestPath()
        // generateSecondPath()
        // generateSecondPath make use of the ShortestPath(the only point at this point)
        // and pick 3 Nodes, 2 from the shortest path and 1 outside
        // connect these 3 Nodes to form the second path
        // START generateSecondPath()
        int num1 = rand.nextInt(ShortestPath.length-2)+1;
        int num2 = rand.nextInt(ShortestPath.length-2)+1;
        while (num2 == num1) {
            num2 = rand.nextInt(ShortestPath.length-2)+1;
        }
        Node otherNode = new Node(rand.nextInt(dimension/2), rand.nextInt(dimension/2));
        while (nodeExistedInShortestPath(otherNode)
                || otherNode.ManhanttanDistance(ShortestPath[num1]) <= 5
                || otherNode.ManhanttanDistance(ShortestPath[num2]) <= 5) {
            otherNode = new Node(rand.nextInt(dimension/2), rand.nextInt(dimension/2));
        }
        // pathToOtherNode(ShortestPath[num1], otherNode)
        // pathToOtherNode is used to connect the Node on the shortest path to the Node outside the path randomly
        // START pathToOtherNode(ShortestPath[num1], otherNode) // the first function call
        Node nodeOnPath = ShortestPath[num1];
        Node target = otherNode;
        row = nodeOnPath.row;
        col = nodeOnPath.col;
        while ((row != target.row) || (col != target.col)) {
            int moveVerticalOrHorizontal = rand.nextInt(2);
            if ((col == target.col) || (moveVerticalOrHorizontal == 0)) {    // move vertically
                if (target.row > row) {
                    if (vertices2MazeRow(row) == repeatedRow) {
                        maze[vertices2MazeRow(row)+2][vertices2MazeCol(col)] = ClearVertex;
                    } else {
                        maze[vertices2MazeRow(row) + 1][vertices2MazeCol(col)] = ClearVertex;
                    }
                    row++;
                } else if (target.row < row){
                    if (vertices2MazeRow(row) == repeatedRow) {
                        maze[vertices2MazeRow(row)-2][vertices2MazeCol(col)] = ClearVertex;
                    } else {
                        maze[vertices2MazeRow(row) - 1][vertices2MazeCol(col)] = ClearVertex;
                    }
                    row--;
                } // no moving if rows are equal
            } else if ((row == target.row) || (moveVerticalOrHorizontal == 1)){ // move horizontally
                if (target.col > col) {
                    if (vertices2MazeCol(col) == repeatedCol) {
                        maze[vertices2MazeRow(row)][vertices2MazeCol(col)+2] = ClearVertex;
                    } else {
                        maze[vertices2MazeRow(row)][vertices2MazeCol(col) + 1] = ClearVertex;
                    }
                    col++;
                } else if (target.col < col){
                    if (vertices2MazeCol(col) == repeatedCol) {
                        maze[vertices2MazeRow(row)][vertices2MazeCol(col)-2] = ClearVertex;
                    } else {
                        maze[vertices2MazeRow(row)][vertices2MazeCol(col) - 1] = ClearVertex;
                    }
                    col--;
                } // no moving if cols are equal
            }
        }
        // END pathToOtherNode(ShortestPath[num1], otherNode) // the first function call
        // START pathToOtherNode(ShortestPath[num2], otherNode);   // the second function call
        nodeOnPath = ShortestPath[num2];
        row = nodeOnPath.row;
        col = nodeOnPath.col;
        while ((row != target.row) || (col != target.col)) {
            int moveVerticalOrHorizontal = rand.nextInt(2);
            if ((col == target.col) || (moveVerticalOrHorizontal == 0)) {    // move vertically
                if (target.row > row) {
                    if (vertices2MazeRow(row) == repeatedRow) {
                        maze[vertices2MazeRow(row)+2][vertices2MazeCol(col)] = ClearVertex;
                    } else {
                        maze[vertices2MazeRow(row) + 1][vertices2MazeCol(col)] = ClearVertex;
                    }
                    row++;
                } else if (target.row < row){
                    if (vertices2MazeRow(row) == repeatedRow) {
                        maze[vertices2MazeRow(row)-2][vertices2MazeCol(col)] = ClearVertex;
                    } else {
                        maze[vertices2MazeRow(row) - 1][vertices2MazeCol(col)] = ClearVertex;
                    }
                    row--;
                } // no moving if rows are equal
            } else if ((row == target.row) || (moveVerticalOrHorizontal == 1)){ // move horizontally
                if (target.col > col) {
                    if (vertices2MazeCol(col) == repeatedCol) {
                        maze[vertices2MazeRow(row)][vertices2MazeCol(col)+2] = ClearVertex;
                    } else {
                        maze[vertices2MazeRow(row)][vertices2MazeCol(col) + 1] = ClearVertex;
                    }
                    col++;
                } else if (target.col < col){
                    if (vertices2MazeCol(col) == repeatedCol) {
                        maze[vertices2MazeRow(row)][vertices2MazeCol(col)-2] = ClearVertex;
                    } else {
                        maze[vertices2MazeRow(row)][vertices2MazeCol(col) - 1] = ClearVertex;
                    }
                    col--;
                } // no moving if cols are equal
            }
        }
        // END pathToOtherNode(ShortestPath[num2], otherNode);   // the second function call
        // END generateSecondPath()
    }

    /**
     * nodeExistedInShortestPath is used to check if the node is on the shortest path
     * It can then be used in choose nodes to connect the second path
     * @author                  mhyuenac
     * @param node              The node that need to check if it is on the shortest path
     * @return                  True if the node is on the shortest path
     */
    protected boolean nodeExistedInShortestPath(Node node) {
        for (Node value : ShortestPath) {
            if (value.EqualNode(node)) {
                return true;
            }
        }
        return false;
    }


    /**
     * vertices2MazeRow is used to convert the row value in MST class to the respective row value in the maze
     * @author                  mhyuenac
     * @param verticesRow       The row value in MST
     * @return                  The row value in maze
     */
    protected int vertices2MazeRow(int verticesRow) {
        if (2 * verticesRow <= repeatedRow) {
            return 2 * verticesRow;
        } else {
            return 2 * verticesRow + 1;
        }
    }

    /**
     * vertices2MazeRow is used to convert the col value in MST class to the respective col value in the maze
     * @author                  mhyuenac
     * @param verticesCol       The col value in MST
     * @return                  The col value in maze
     */
    protected int vertices2MazeCol(int verticesCol) {
        if (2*verticesCol<=repeatedCol) {
            return 2*verticesCol;
        } else {
            return 2*verticesCol+1;
        }
    }
    // For debug
//    public String printMaze() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Entry point is: " + Entry.row + "," + " " + Entry.col + "\n");
//        sb.append("Exit point is: " + Exit.row + "," + " " + Exit.col + "\n");
//        for (int[] row: maze) {
//            sb.append(Arrays.toString(row) + "\n");
//        }
//        return sb.toString();
//    }
//    public String getSymbolicMaze() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < dimension+2; i++) {
//            for (int j = 0; j < dimension + 2; j++) {
//                if ((i == 0) || (i == dimension + 1) || (j == 0) || (j == dimension + 1)) {
//                    sb.append("#");
//                    sb.append("  ");
//                } else {
//                    sb.append(maze[i-1][j-1] == 0 ? "*" : " ");
//                    sb.append("  ");
//                }
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
}
