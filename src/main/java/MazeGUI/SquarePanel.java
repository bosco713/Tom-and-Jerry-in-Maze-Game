package MazeGUI;

import java.awt.Color;
import javax.swing.JPanel;

public class SquarePanel extends JPanel{

    // private static final long serialVersionUID = 1L;

    /**
     * SquarePanel is a class representing all the square in the window
     * Constructor construct an empty window
     * @author      mhyuenac
     * @param d     The color to be set
     */
    public SquarePanel(Color d){
        this.setBackground(d);
    }

    /**
     * ChangeColor is used to change the color of the square
     * @author      mhyuenac
     * @param d     The new color
     */
    public void ChangeColor(Color d){
        this.setBackground(d);
        this.repaint();
    }

}