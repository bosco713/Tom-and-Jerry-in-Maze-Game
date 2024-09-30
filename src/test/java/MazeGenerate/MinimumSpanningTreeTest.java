package MazeGenerate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumSpanningTreeTest {
    int testing_dim = 3;
    int testing_max = 10;
    Node Entry = new Node(0, 0);
    int rand_seed = 10;

    @Test
    void TestSetRandSeed() {
        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree(testing_dim, testing_max, Entry);
        minimumSpanningTree.SetRandSeed(rand_seed);     // target function
        int answer = 3;
        assertEquals(answer, minimumSpanningTree.rand.nextInt(10));
    }

    @Test
    void TestGetParentArray() {
        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree(testing_dim, testing_max, Entry);
        minimumSpanningTree.SetRandSeed(rand_seed);
        minimumSpanningTree.PrimsAlgorithm();
        Node[][] ParentArray = minimumSpanningTree.GetParentArray();    // target function
        Node[][] answer = new Node[3][3];
        answer[0][1] = new Node(0, 0);
        answer[0][2] = new Node(0, 1);
        answer[1][0] = new Node(0, 0);
        answer[1][1] = new Node(0, 1);
        answer[1][2] = new Node(0, 2);
        answer[2][0] = new Node(2, 1);
        answer[2][1] = new Node(1, 1);
        answer[2][2] = new Node(1, 2);
        for (int i=1; i<8; ++i) {
            assertTrue(answer[i/3][i%3].EqualNode(ParentArray[i/3][i%3]));
        }
    }

    @Test
    void TestPrimsAlgorithm() {
        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree(testing_dim, testing_max, Entry);
        minimumSpanningTree.SetRandSeed(31);
        minimumSpanningTree.PrimsAlgorithm();       // target function
        boolean[][] TrueArray = new boolean[3][3];
        for (int i=0; i<9; ++i) {
            TrueArray[i/3][i%3] = true;
        }
        assertArrayEquals(TrueArray[0], minimumSpanningTree.verticesArray[0]);
        assertArrayEquals(TrueArray[1], minimumSpanningTree.verticesArray[1]);
        assertArrayEquals(TrueArray[2], minimumSpanningTree.verticesArray[2]);
    }
}