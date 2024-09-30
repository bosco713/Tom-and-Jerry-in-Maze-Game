package MazeGenerate;

import org.junit.jupiter.api.Test;

class MazeFactoryTest {
    @Test
    void TestWriteMazeInformationInCSV() {
        MazeFactory mazeFactory = new MazeFactory();
        mazeFactory.WriteMazeInformationInCSV();    // target function
    }
}