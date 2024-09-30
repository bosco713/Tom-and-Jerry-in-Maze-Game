package MazeGUI;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JerryControllerTest {

    @Test
    void runTest() { //OK
        PlayGUI.Grid = new ArrayList<>();
        int[][] nodes = MazeGUI.Window.readCSV("./map/EntryAndExitNode.csv");
        PlayGUI.Entry = nodes[0];
        PlayGUI.Exit = nodes[1];

        int[][] maze = MazeGUI.Window.readCSV("./map/MazeMap.csv");

        ArrayList<VertexLocation> RowOfVertex;
        for (int row = 0; row < 30; ++row) {
            RowOfVertex = new ArrayList<>();
            for (int col = 0; col < 30; ++col) {
                if (maze[row][col] == 0) {
                    ClearVertex clearVertex = new ClearVertex(row, col);
                    RowOfVertex.add(clearVertex);
                } else {
                    Barrier barrier = new Barrier(row, col);
                    RowOfVertex.add(barrier);
                }
            }
            PlayGUI.Grid.add(RowOfVertex);
        }

        Tuple Jerry_position = new Tuple(PlayGUI.Entry[1],PlayGUI.Entry[0]);
        JerryController Jerry = new JerryController(Jerry_position);

        Tuple Tom_position = new Tuple(PlayGUI.Exit[1],PlayGUI.Exit[0]);
        TomController Tom = new TomController(Tom_position);

        Jerry.start();
    }

    @Test
    void CheckCollisionTest() { //OK
        PlayGUI.Grid = new ArrayList<>();
        PlayGUI.Entry = new int[]{0, 0};
        PlayGUI.Exit = new int[]{2,2};

        int[][] maze = {{0, 0, 0},{0, 0, 0},{0, 0, 0}};

        ArrayList<VertexLocation> RowOfVertex;
        for (int row = 0; row < 3; ++row) {
            RowOfVertex = new ArrayList<>();
            for (int col = 0; col < 3; ++col) {
                if (maze[row][col] == 0) {
                    ClearVertex clearVertex = new ClearVertex(row, col);
                    RowOfVertex.add(clearVertex);
                } else {
                    Barrier barrier = new Barrier(row, col);
                    RowOfVertex.add(barrier);
                }
            }
            PlayGUI.Grid.add(RowOfVertex);
        }

        Tuple Jerry_position = new Tuple(0,0);
        JerryController Jerry = new JerryController(Jerry_position);

        Tuple Tom_position = new Tuple(0,0);
        TomController Tom = new TomController(Tom_position);

        Tom.start();
        Jerry.start();
    }

    //    @Test
//    void pauserTest(){
//    }

    @Test
    void stopTheGameTest() { //OK
        PlayGUI.Grid = new ArrayList<>();
        PlayGUI.Entry = new int[]{0, 0};
        PlayGUI.Exit = new int[]{2,2};

        int[][] maze = {{0, 0, 0},{0, 0, 0},{0, 0, 0}};

        ArrayList<VertexLocation> RowOfVertex;
        for (int row = 0; row < 3; ++row) {
            RowOfVertex = new ArrayList<>();
            for (int col = 0; col < 3; ++col) {
                if (maze[row][col] == 0) {
                    ClearVertex clearVertex = new ClearVertex(row, col);
                    RowOfVertex.add(clearVertex);
                } else {
                    Barrier barrier = new Barrier(row, col);
                    RowOfVertex.add(barrier);
                }
            }
            PlayGUI.Grid.add(RowOfVertex);
        }

        Tuple Jerry_position = new Tuple(2,2);
        JerryController Jerry = new JerryController(Jerry_position);
        Jerry.start();
    }

    @Test
    void moveTest() { //OK
        PlayGUI.Grid = new ArrayList<>();
        PlayGUI.Entry = new int[]{0, 0};
        PlayGUI.Exit = new int[]{2,2};

        int[][] maze = {{0, 0, 0},{0, 0, 0},{0, 0, 0}};

        ArrayList<VertexLocation> RowOfVertex;
        for (int row = 0; row < 3; ++row) {
            RowOfVertex = new ArrayList<>();
            for (int col = 0; col < 3; ++col) {
                if (maze[row][col] == 0) {
                    ClearVertex clearVertex = new ClearVertex(row, col);
                    RowOfVertex.add(clearVertex);
                } else {
                    Barrier barrier = new Barrier(row, col);
                    RowOfVertex.add(barrier);
                }
            }
            PlayGUI.Grid.add(RowOfVertex);
        }

        Tuple Jerry_position = new Tuple(1,1);
        JerryController Jerry = new JerryController(Jerry_position);

        Jerry.move(1);
        Jerry.move(2);
        Jerry.move(3);
        Jerry.move(4);
    }

    @Test
    void DeleteOldPosTest() { //NO
        PlayGUI.Grid = new ArrayList<>();
        PlayGUI.Entry = new int[]{0, 0};
        PlayGUI.Exit = new int[]{2, 2};

        int[][] maze = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        ArrayList<VertexLocation> RowOfVertex;
        for (int row = 0; row < 3; ++row) {
            RowOfVertex = new ArrayList<>();
            for (int col = 0; col < 3; ++col) {
                if (maze[row][col] == 0) {
                    ClearVertex clearVertex = new ClearVertex(row, col);
                    RowOfVertex.add(clearVertex);
                } else {
                    Barrier barrier = new Barrier(row, col);
                    RowOfVertex.add(barrier);
                }
            }

            Tuple Jerry_position = new Tuple(0, 0);
            JerryController Jerry = new JerryController(Jerry_position);

            Tuple Tom_position = new Tuple(0, 2);
            TomController Tom = new TomController(Tom_position);

            Jerry_position.Row = 1;
            Jerry_position.Old_Row = 0;
            Jerry_position.Col = 0;
            Jerry_position.Old_Col = 0;

            Jerry.start();
        }
    }
}