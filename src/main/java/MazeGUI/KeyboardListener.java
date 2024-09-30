package MazeGUI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter{

    public void keyPressed(KeyEvent e){
        //switch direction only -> need check again ClearVertex later!!
        switch(e.getKeyCode()){     //1:right,2:left,3:top,4:bottom
            case 40:	// -> Bottom
                if(JerryController.Jerry_Pos.Row < 29) {
                    if (PlayGUI.Grid.get(JerryController.Jerry_Pos.Row + 1).get(JerryController.Jerry_Pos.Col).typeOfVertex != 1) {
                        JerryController.jerry_direction = 4;
                    }
                }
                break;
            case 39:	// -> Right
                if(JerryController.Jerry_Pos.Col < 29) {
                    if (PlayGUI.Grid.get(JerryController.Jerry_Pos.Row).get(JerryController.Jerry_Pos.Col + 1).typeOfVertex != 1) {
                        JerryController.jerry_direction = 1;
                    }
                }
                break;
            case 38:	// -> Top
                if(JerryController.Jerry_Pos.Row > 0) {
                    if (PlayGUI.Grid.get(JerryController.Jerry_Pos.Row - 1).get(JerryController.Jerry_Pos.Col).typeOfVertex != 1) {
                        JerryController.jerry_direction = 3;
                    }
                }
                break;

            case 37: 	// -> Left
                if(JerryController.Jerry_Pos.Col > 0) {
                    if (PlayGUI.Grid.get(JerryController.Jerry_Pos.Row).get(JerryController.Jerry_Pos.Col - 1).typeOfVertex != 1) {
                        JerryController.jerry_direction = 2;
                    }
                }
                break;
            default: 	break;
        }
    }

}
