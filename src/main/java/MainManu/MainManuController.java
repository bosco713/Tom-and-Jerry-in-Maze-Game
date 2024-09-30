package MainManu;

import java.io.File;
import MazeGenerate.MazeFactory;
import MazeGenerate.MazeMapGUI;
import MazeGUI.PlayGUI;
import javafx.fxml.FXML;
import pathfunction.ShortestPathGUI;


public class MainManuController {
    /**
     * MainManuController calls the function when certain button is pressed
     * onFunctionAButtonClick is used to call function that write map data into csv file and then show the GUI window displaying the map data
     * @author      mhyuenac
     */
    @FXML
    protected void onFunctionAButtonClick() {
        File mazeFile = new File("./map/MazeMap.csv");
        File nodeFile = new File("./map/EntryAndExitNode.csv");
        if ((!mazeFile.exists() || mazeFile.length() != 2670) || (!nodeFile.exists() || nodeFile.length() != 12)) {
            // 2670 = (30 nodes + 29 commas + 29 spaces + 1 end of line char) * 30 lines
            // 12 = (2*strlen("29") + 2*strlen("0") + 2*strlen(", ") + 2 end of line chars)
            MazeFactory mazeFactory = new MazeFactory();
            mazeFactory.WriteMazeInformationInCSV();
        }
        MazeMapGUI mazeMapGUI = new MazeMapGUI();
        mazeMapGUI.BuildMazeMapGUI();
    }

    @FXML
    protected void onFunctionBButtonClick() {
        File file = new File("./map/MazeMap.csv");

        if (!file.exists() || file.length() == 0) {
            MazeFactory mazeFactory = new MazeFactory();
            mazeFactory.WriteMazeInformationInCSV();
        }

        ShortestPathGUI.constructShortestPathGUI();
    }

    @FXML
    protected void onFunctionCButtonClick() {
        PlayGUI PlayGame = new PlayGUI();

        PlayGame.setTitle("Maze Game");
        PlayGame.setSize(700,700);
        PlayGame.setVisible(true);
        //PlayGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
