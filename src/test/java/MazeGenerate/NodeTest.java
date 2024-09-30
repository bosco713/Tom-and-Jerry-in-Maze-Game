package MazeGenerate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    /**
     * Node Test is a class to perform unit test on Node class
     * @author      mhyuenac
     * @date        9-11-2023
     */
    Node TestNodeA = new Node(0, 0);
    Node TestNodeB = new Node(10, 5);
    Node TestNodeC = new Node(0, 0);
    @Test
    void TestEqualNode() {
        assertFalse(TestNodeA.EqualNode(TestNodeB));
        assertTrue(TestNodeA.EqualNode(TestNodeC));     // target function
    }
    @Test
    void TestManhanttanDistance() {
        assertEquals(TestNodeA.ManhanttanDistance(TestNodeB), 15);      // target function
    }
}