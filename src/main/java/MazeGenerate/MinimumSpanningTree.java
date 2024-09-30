package MazeGenerate;

import java.util.Random;
public class MinimumSpanningTree {
    private final int dimension;
    private final int max;
    private final Node Entry;
    protected final boolean[][] verticesArray;
    private final int [][] keyArray;
    private final Node[][] parentArray;
    private final int[][] horizontalEdgeArray;
    private final int[][] verticalEdgeArray;
    private final MinHeap minPriorityQueue;
    protected final Random rand = new Random();

    /**
     * MazeGenerate.MinimumSpanningTree is used to construct the path of the maze
     * verticesArray        is used to mark if the node has visited
     * keyArray             is used to store the minimum weight correcting to the node (initial = max + 1 as never visited)
     * parentArray          is used to mark down which parent node visit the target node (target node as a minimum weight to the parent node but not the other node)
     *                      can have different choice (multiple node can have the same weight to the target node --> just one of the minimum
     *                      to fix this problem --> increase the random value generate --> data more spread --> lower chance of having the same weight)
     * horizontalEdgeArray  is used store all the horizontal edge weight value
     * verticalEdgeArray    is used store all the vertical edge weight value
     * Graph showing how the data is store:
     * V[][]:   verticesArray
     * h[][]:   horizontalEdgeArray
     * v[][]:   verticalEdgeArray
     *  V[0][0] --- h[0][0] --- V[0][1] --- h[0][1] --- V[0][2]
     *     |                       |                       |
     *  v[0][0]                 v[0][1]                 v[0][2]
     *     |                       |                       |
     *  V[1][0] --- h[1][0] --- V[1][1] --- h[1][1] --- V[1][2]
     *     |                       |                       |
     *  v[1][0]                 v[1][1]                 v[1][2]
     *     |                       |                       |
     *  V[2][0] --- h[2][0] --- V[2][1] --- h[2][1] --- V[2][2]
     * minPriorityQueue     is the manner how a minHeap is shown in a hash array form
     * @author          mhyuenac
     * @param dim       The length of the page of node (100 node in a page, dim = 10)
     * @param max       The maximum of the random value it will assign to the weight (edge)
     * @param node      The Entry node
     */
    MinimumSpanningTree(int dim, int max, Node node) {
        dimension = dim;
        this.max = max;
        Entry = new Node(node.row/2, node.col/2);
        verticesArray = new boolean[dimension][dimension];
        keyArray = new int[dimension][dimension];
        parentArray = new Node[dimension][dimension];
        horizontalEdgeArray = new int[dimension][dimension-1];
        verticalEdgeArray = new int[dimension-1][dimension];
        minPriorityQueue = new MinHeap(dimension, max);
    }

    /**
     * SetRandSeed is used in unit testing --> set a specific seed see if the output is the same as expected
     * @author          mhyuenac
     * @param seed      The random seed to be set
     */
    public void SetRandSeed(int seed) {
        rand.setSeed(seed);
    }

    /**
     * GetParentArray is used to return the Parent Array to MazeGenerate.MazeGenerator for constructing the linkage between the nodes
     * @author      mhyuenac
     * @return      The ParentArray after building the MinimumSpanningTree
     */
    public Node[][] GetParentArray() {
        return parentArray;
    }


    /**
     * primsAlgorithm is one of the algorithm that builds a minimum spanning tree
     * it makes use of MazeGenerate.MinHeap data structure
     * while (the minPriorityQueue is not empty):
     *      extract the node with minimum weight
     *      mark it as visited
     *      process the nearby nodes (change the key value of nearby nodes)
     */
    public void PrimsAlgorithm() {
        // initialization:
        // 1. keyArray          --> set all to max+1
        // 2. two EdgeArray     --> set all to random value (0,max]
        // 3. verticesArray     --> set all to false (unvisited)
        // 4. Insert all the element in keyArray to minPriorityQueue
        // START initialization:
        // initializeKeyArray()
        // keyArray         --> set all to max+1 (a value that means un-visit)
        for (int row = 0; row < dimension; ++row) {
            for (int col = 0; col < dimension; ++col) {
                keyArray[row][col] = max + 1;
            }
        }
        keyArray[Entry.row][Entry.col] = 0;

        // assignRandomValueToMST()
        // two EdgeArray    --> set all to random value (0,max] (the weight value of each node connection
        //                  --> become the key value if it is the minimum weight
        for (int row = 0; row < dimension; ++row) {
            for (int col = 0; col < dimension; ++col) {
                if (col != dimension-1) {
                    horizontalEdgeArray[row][col] = rand.nextInt(max) + 1;
                }
                if (row != dimension-1) {
                    verticalEdgeArray[row][col] = rand.nextInt(max) + 1;
                }
            }
        }

        // initializeVerticesArray()
        // verticesArray    --> set all to false (unvisited)
        for (int row=0; row<dimension; ++row) {
            for (int col=0; col<dimension; ++col) {
                verticesArray[row][col] = false;
            }
        }

        // insertKeyIntoPriQ()
        // Insert all the element in keyArray and its node (the coordinate) to minPriorityQueue
        // Initially: all nodes are un-visit --> key value will be max+1
        for (int row=0; row<dimension; ++row) {
            for (int col=0; col<dimension; ++col) {
                minPriorityQueue.Insert(new Node(row, col), keyArray[row][col]); // key = the shortest weight
            }
        }
        // END initialization
        while (minPriorityQueue.NotEmpty()) {
            Node minWeightedNode = minPriorityQueue.ExtractMin();
            verticesArray[minWeightedNode.row][minWeightedNode.col] = true;
            Node centreNode = new Node(minWeightedNode.row, minWeightedNode.col);
            // processNearbyVertices is used to update the key value of the nearby vertices
            // need to check the nearby vertices:
            // 1. On Grid?      --> valid coordinate
            // 2. Not Corner?   --> not processing diagonal neighbors
            // 3. Not itself?   --> not processing the centreNode itself
            // 4. Not visit?    --> not yet visit --> not yet connected --> need connection
            // processNearbyVertices(new Node(minWeightedNode.row, minWeightedNode.col));
            for (int row=centreNode.row-1; row<= centreNode.row+1; ++row) {
                for (int col=centreNode.col-1; col<= centreNode.col+1; ++col) {
                    if (((row>=0 && row<dimension) && (col>=0 && col<dimension))        // pointOnGrid(row, col)
                            && ((centreNode.row == row) || (centreNode.col == col))     // nodeNotCorner(centreNode, row, col)
                            && !((centreNode.row == row) && (centreNode.col == col))    // nodeNotItself(centreNode, row, col)
                            && !(verticesArray[row][col])) {                            // pointNotVisited(row, col)
                        Node adjNode = new Node(row, col);
                        // What is the distance:
                        // If adjNode is on the same row --> horizontal neighbor --> use the weight in horizontalEdgeArray
                        // Else if adjNode is on the same col --> vertical neighbor --> use the weight in verticalEdgeArray
                        int distance = max+1;
                        if (centreNode.row == adjNode.row) { // horizontal neighbors --> check the horizontal array
                            if (centreNode.col < adjNode.col) { // nodeA is on the left of nodeB
                                distance =  horizontalEdgeArray[centreNode.row][centreNode.col];
                            } else { // nodeA is on the right of nodeB
                                distance =  horizontalEdgeArray[adjNode.row][adjNode.col];
                            }
                        } else if (centreNode.col == adjNode.col) { // vertical neighbors --> check the vertical array
                            if (centreNode.row < adjNode.row) { // nodeA is on the top of nodeB
                                distance =  verticalEdgeArray[centreNode.row][centreNode.col];
                            } else { // nodeA is on the bottom of nodeB
                                distance =  verticalEdgeArray[adjNode.row][adjNode.col];
                            }
                        }
                        if (distance<keyArray[row][col]) {
                            parentArray[row][col] = centreNode;
                            keyArray[row][col] = distance;
                            minPriorityQueue.DecreaseKey(new Node(row, col), distance);
                        }
                    }
                }
            }
        }
    }

    // For debug
//    public void printMST() {
//        // System.out.println("vertciesArray is:\n" + printVertciesArray());
//        System.out.println("keyArray is:\n" + printKeyArray());
//        System.out.println("keyArray1 is:\n" + printKeyArray1());
//        System.out.println("edgeArray[0] is:\n" + printHorizontalEdgeArray());
//        System.out.println("edgeArray[1] is:\n" + printVerticalEdgeArray());
//    }
//    public String printVertciesArray() {
//        StringBuilder sb = new StringBuilder();
//        for (int row=0; row<dimension; ++row) {
//            for (int col=0; col<dimension; ++col) {
//                if (verticesArray[row][col]) {
//                    sb.append("1 ");
//                } else {
//                    sb.append("0 ");
//                }
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
//    public String printKeyArray() {
//        StringBuilder sb = new StringBuilder();
//        for (int[] row: keyArray) {
//            sb.append(Arrays.toString(row) + "\n");
//        }
//        return sb.toString();
//    }
//    public String printKeyArray1() {
//        StringBuilder sb = new StringBuilder();
//        for (int row=0; row<dimension; ++row) {
//            for (int col=0; col<dimension; ++col) {
//                if (keyArray[row][col]!=max+1) {
//                    sb.append("1 ");
//                } else {
//                    sb.append("0 ");
//                }
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
//    public String printHorizontalEdgeArray() {
//        StringBuilder sb = new StringBuilder();
//        for (int[] row: horizontalEdgeArray) {
//            sb.append(Arrays.toString(row) + "\n");
//        }
//        return sb.toString();
//    }
//    public String printVerticalEdgeArray() {
//        StringBuilder sb = new StringBuilder();
//        for (int[] row: verticalEdgeArray) {
//            sb.append(Arrays.toString(row) + "\n");
//        }
//        return sb.toString();
//    }
}
