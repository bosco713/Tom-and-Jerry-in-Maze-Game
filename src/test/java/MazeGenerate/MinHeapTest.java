package MazeGenerate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {
    int test_dim = 10;
    int test_max = 10;

    /**
     * MinHeapTest is a class use to test MinHeap class
     * @author      mhyuenac
     * DID NOT TEST SWAP
     */
    @Test
    void TestInsert() {
        Node testNodeA = new Node(0, 10);
        Node testNodeB = new Node(0, 9);
        Node testNodeC = new Node(0, 8);
        Node testNodeD = new Node(0, 7);
        Node testNodeE = new Node(0, 6);
        Node answer = new Node(0, 6);
        MinHeap minHeap = new MinHeap(test_dim, test_max);
        minHeap.Insert(testNodeA, 10);
        minHeap.Insert(testNodeB, 9);
        minHeap.Insert(testNodeC, 8);
        minHeap.Insert(testNodeD, 7);
        minHeap.Insert(testNodeE, 6);        // target function

        assertTrue(answer.EqualNode(minHeap.ExtractMin()));
    }
    @Test
    void TestExtractMin() {
        int[] testing_set = {10, 4, 6, 8, 1, 3, 2, 5, 7, 9};
        int[] answer = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] testing_result = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        MinHeap minHeap = new MinHeap(test_dim, test_max);
        for (int i=0; i<test_dim; ++i) {
            minHeap.Insert(new Node(0, testing_set[i]), testing_set[i]);
        }
        for (int i=0; i<test_dim; ++i) {
            Node minNode = minHeap.ExtractMin();        // target function
            testing_result[i] = minNode.col;
        }
        assertArrayEquals(answer, testing_result);
    }

    @Test
    void TestDecreaseKey() {
        int[] testing_set = {10, 4, 6, 8, 1, 3, 2, 5, 7, 9};
        int[] answer = {1, 2, 9, 3, 4, 5, 6, 7, 8, 10};
        int[] testing_result = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        MinHeap minHeap = new MinHeap(test_dim, test_max);
        for (int i = 0; i < test_dim; ++i) {
            minHeap.Insert(new Node(0, testing_set[i]), testing_set[i]);
        }
        minHeap.DecreaseKey(new Node(0, 9), 2);         // target function
        for (int i=0; i<test_dim; ++i) {
            Node minNode = minHeap.ExtractMin();
            testing_result[i] = minNode.col;
        }
        assertArrayEquals(answer, testing_result);
    }

    @Test
    void notEmpty() {
        MinHeap minHeap = new MinHeap(test_dim, test_max);
        assertFalse(minHeap.NotEmpty());        // target function
        minHeap.Insert(new Node(0, 0), 10);
        assertTrue(minHeap.NotEmpty());
    }

    @Test
    void TestSwap() {
        MinHeap minHeap = new MinHeap(test_dim, test_max);
        minHeap.Insert(new Node(0, 0), 0);
        minHeap.Insert(new Node(0, 1), 1);
        minHeap.swap(0, 1);
        assertTrue(new Node(0, 1).EqualNode(minHeap.ExtractMin()));
        assertTrue(new Node(0, 0).EqualNode(minHeap.ExtractMin()));
    }
}