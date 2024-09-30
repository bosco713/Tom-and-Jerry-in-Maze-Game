package MazeGUI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import javax.swing.*;

class WindowTest {

    @Test
    void TestReadCSV() {
        Window window = new Window();
        int[][] testing = Window.readCSV("./map/TestingFile.csv");      // target function
        int[][] answer = new int[2][2];
        answer[0][0] = 29;
        answer[0][1] = 29;
        answer[1][0] = 0;
        answer[1][1] = 0;
        assertArrayEquals(answer[0], testing[0]);
        assertArrayEquals(answer[1], testing[1]);
    }
    @Test
    void TestBuildGridWithMazeMap() {
        Window window = new Window();
        window.BuildGridWithMazeMap();
    }

    @Test
    void TestShowMaze() {
        //Creating the window with all its awesome snaky features
        Window window = new Window();

        //Setting up the window settings
        window.SetUpMazePanelUsingGrid();
        window.ShowMaze();          // target function
        window.setTitle("MazeMap");
        window.setSize(300,300);
        window.setVisible(true);
    }

    @Test
    void TestFunctionAButton() {
        Window window = new Window();
        window.SetUpMazePanelUsingGrid();
        window.ShowMaze();
        window.button.doClick();    // target function = "Generate new map" button
    }

    @Test
    void TestShortestPath() {
        Window window = new Window();
        window.showShortestPath();
    }
}