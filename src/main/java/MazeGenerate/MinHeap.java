package MazeGenerate;

public class MinHeap {
    private final Node[] NodeHeap; // parallel array
    private final int[] NodeWeightValue;    // parallel array
    private final int size;
    private int current = 0;
    private final int max;

    /**
     * MazeGenerate.MinHeap is used to extract the minimum weight node for MazeGenerate.MiniSpanningTree
     * The minHeap is store in hash way in a Node array
     * NodeHeap and NodeWeightValue are parallel arrays (NodeHeap[0] has corresponding weight value = NodeWeightValue[0])
     *              N[0]
     *         N[1]     N[2]
     *      N[3] N[4] N[5] N[6]
     * @author      mhyuenac
     * @param dim   The length of one page of node  --> size = 100 nodes for page dim = 10
     * @param max   The maximum value of the weight (depends on the max{random weight})
     */
    MinHeap(int dim, int max) {
        size = dim*dim;
        NodeHeap = new Node[size];
        NodeWeightValue = new int[size];
        this.max = max;
        for (int i=0; i<size; ++i) {
            NodeWeightValue[i] = max+1;
        }
    }

    /**
     * Insert is a standard operation of a minHeap (check the parent nodes bigger than current node --> bubble up)
     * @author          mhyuenac
     * @param node      The node coordinate (need to store in the minHeap together --> maze need the parent array and the coordinate to contruct the maze
     * @param weight    The node's weight (In this case, it is randomly assume in MazeGenerate.MinimumSpanningTree)
     */
    public void Insert(Node node, int weight){
        NodeHeap[current] = node;
        NodeWeightValue[current] = weight;
        int performBubbleUpIndex = current;
        current++;
        while (NodeWeightValue[performBubbleUpIndex]<NodeWeightValue[(performBubbleUpIndex-1)/2]
                && performBubbleUpIndex>0) {
            // only enter this loop when NodeWeightValue[current] is smaller than its parent
            swap(performBubbleUpIndex, (performBubbleUpIndex-1)/2);
            performBubbleUpIndex = (performBubbleUpIndex-1)/2;
        }
    }

    /**
     * ExtractMin is a standard operation of a minHeap (check the child node (left first) smaller than current node --> bubble down)
     * @author      mhyuenac
     * @return      The node with the minimum weight (just the coordinate)
     */
    public Node ExtractMin() {
        Node returnNode = new Node(NodeHeap[0].row, NodeHeap[0].col);     // store the return value
        // removeSmallest():
        // Replace the first node with last node
        // the original last node will reset its weight --> avoid using it again
        swap(0, (--current));
        NodeWeightValue[current] = max+1;
        // End of removeSmallest()
        int currentIndex = 0;
        int left = NodeWeightValue[1]; // null value? need test case here
        int right = NodeWeightValue[2];
        while (NodeWeightValue[currentIndex] > Math.min(left, right)) {
            if (left < right) {
                swap(currentIndex, 2*currentIndex+1);
                currentIndex = 2*currentIndex+1;
            } else {
                swap(currentIndex, 2*(currentIndex+1));
                currentIndex = 2*(currentIndex+1);
            }
            if ((2*currentIndex+1 >= size) || (2*(currentIndex+1) >= size)) break;
            left = NodeWeightValue[2*currentIndex+1];
            right = NodeWeightValue[2*(currentIndex+1)];
        }
        return returnNode;
    }

    /**
     * DecreaseKey is a standard operation of minHeap (locate the node in the array, change its key value)
     * @author              mhyuenac
     * @param node          The node (coordinate) to be changed
     * @param new_value     The new key value
     */
    public void DecreaseKey(Node node, int new_value) {
        int index;
        for (index=0; !node.EqualNode(NodeHeap[index]); ++index); // what if the node isn't inside
        NodeWeightValue[index] = new_value;
        while (NodeWeightValue[index]<NodeWeightValue[(index-1)/2]
                && index>0) {
            // only enter this loop when NodeWeightValue[current] is smaller than its parent
            swap(index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    /**
     * NotEmpty is used to show if the minHeap is empty
     * @author      mhyuenac
     * @return      true when minHeap is not empty
     */
    public boolean NotEmpty() {
        return current>0;
    }

    /**
     * swap is used to swap two node and its respective weight value inside the minHeap using the hash array
     * call a = 0 when remove smallest
     * @author      mhyuenac
     * @param a     First node index
     * @param b     Second node index
     */
    protected void swap(int a, int b) {
        Node tempNode = NodeHeap[b];
        int tempWeight = NodeWeightValue[b];
        NodeHeap[b] = NodeHeap[a];
        NodeWeightValue[b] = NodeWeightValue[a];
        NodeHeap[a] = tempNode;
        NodeWeightValue[a] = tempWeight;
    }
}
