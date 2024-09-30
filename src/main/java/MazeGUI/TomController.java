package MazeGUI;

import pathfunction.ShortestPath;

import java.awt.*;
import java.util.ArrayList;

public class TomController extends Thread {
    //ArrayList<ArrayList<VertexLocation>> Squares = new ArrayList<ArrayList<VertexLocation>>();
    static Tuple Tom_Pos;
    long speed = 240;   //smaller = faster

    //Constructor of ControllerThread
    TomController(Tuple positionDepart){
        //Get all the threads
//        Squares = PlayGUI.Grid;
        Tom_Pos = new Tuple(positionDepart.Row,positionDepart.Col);
    }

    //Important part :
    public void run() {
        while(true){
            //check collision before move
            CheckCollision();
            move();
            DeleteOldPos();
            //check collision after move
            CheckCollision();
            pauser();
        }
    }

    //check collision
    private void CheckCollision(){
        if((JerryController.Jerry_Pos.Row == PlayGUI.Exit[1] && JerryController.Jerry_Pos.Col == PlayGUI.Exit[0]) || (Tom_Pos.Row == JerryController.Jerry_Pos.Row && Tom_Pos.Col== JerryController.Jerry_Pos.Col)){
            stopTheGame();      //Exit or Caught
        }
    }

    //delay between each move of Tom
    private void pauser(){
        try {
            sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();}
    }

    //Stops The Game
    private void stopTheGame(){
        if(Tom_Pos.Row == JerryController.Jerry_Pos.Row && Tom_Pos.Col == JerryController.Jerry_Pos.Col) {
            System.out.println("Tom Catches Jerry! You lose!\n");
        }
        while(true){
            pauser();
        }
    }

    //Moves Tom and refreshes the positions in the arraylist
    private void move(){
        int[][] maze = MazeGUI.Window.readCSV("./map/MazeMap.csv");
        int[] Tom_current = {Tom_Pos.Row, Tom_Pos.Col};
        int[] Jerry_current = {JerryController.Jerry_Pos.Row, JerryController.Jerry_Pos.Col};
        int[][] Tom_Path = ShortestPath.getPath(maze, Tom_current, Jerry_current);
        if(Tom_Path[1][0] != Tom_Path[0][0] || Tom_Path[1][1] != Tom_Path[0][1]) {
            Tom_Pos.ChangeData(Tom_Path[1][0], Tom_Path[1][1]);
        }
//        int row_diff = Tom_Path[1][0] - Tom_Path[0][0];     //x->y
//        int col_diff = Tom_Path[1][1] - Tom_Path[0][1];     //y->x
//        if(PlayGUI.Grid.get(Tom_Pos.Row + row_diff).get(Tom_Pos.Col + col_diff).GetType() != 1) {
//            Tom_Pos.ChangeData((Tom_Pos.Row + row_diff), (Tom_Pos.Col + col_diff));
//        }
    }

    //Refresh the squares that needs to be
    private void DeleteOldPos(){
        if(Tom_Pos.Row != Tom_Pos.Old_Row || Tom_Pos.Col != Tom_Pos.Old_Col) {
            //add color to new position
            PlayGUI.Grid.get(Tom_Pos.Row).get(Tom_Pos.Col).square.ChangeColor(Color.lightGray);
            //delete color of old position
            if(Tom_Pos.Old_Row == PlayGUI.Exit[1] && Tom_Pos.Old_Col == PlayGUI.Exit[0]){
                PlayGUI.Grid.get(Tom_Pos.Old_Row).get(Tom_Pos.Old_Col).square.ChangeColor(Color.green);
            }
            else{
                PlayGUI.Grid.get(Tom_Pos.Old_Row).get(Tom_Pos.Old_Col).square.ChangeColor(Color.white);
            }
        }
    }
}
