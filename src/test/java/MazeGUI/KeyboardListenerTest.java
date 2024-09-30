package MazeGUI;

import com.sun.source.doctree.AttributeTree;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KeyboardListenerTest {

    @Test
    void keyPressedTest() {
        PlayGUI.Grid = new ArrayList<>();

        int[][] maze = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};

        ArrayList<VertexLocation> RowOfVertex;
        for (int row = 0; row < 5; ++row) {
            RowOfVertex = new ArrayList<>();
            for (int col = 0; col < 5; ++col) {
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

        KeyboardListener keyboard = new KeyboardListener();
        KeyEvent press = new KeyEvent(new Component(){}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'Z');

        for (int i = 36; i < 41; ++i) {
            press.setKeyCode(i);
            keyboard.keyPressed(press);
        }
    }
}