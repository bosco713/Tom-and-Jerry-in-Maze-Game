package pathfunction;

import MazeGenerate.MazeFactory;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * A test class for the {@link ShortestPathGUI} class.
 *
 * @author  hktsangad
 */
public class ShortestPathGUITest {

    /**
     * Tests the {@link ShortestPathGUI#constructShortestPathGUI()} method.
     *
     * @author  hktsangad
     */
    @Test
    void constructShortestPathGUITest() {
        File file = new File("./map/MazeMap.csv");

        if (!file.exists() || file.length() == 0) {
            MazeFactory mazeFactory = new MazeFactory();
            mazeFactory.WriteMazeInformationInCSV();
        }

        ShortestPathGUI.constructShortestPathGUI(); // target function
    }
}
