package MazeGUI;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SquarePanelTest {

    @Test
    void changeColor() {
        SquarePanel squarePanel = new SquarePanel(Color.red);
        squarePanel.ChangeColor(Color.green);       // target function
        assertEquals(Color.green, squarePanel.getBackground());
    }
}