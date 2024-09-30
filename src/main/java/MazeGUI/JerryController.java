package MazeGUI;


import java.awt.*;

public class JerryController extends Thread {
    static Tuple Jerry_Pos;
    long speed = 250;   //larger = slower
    protected static int jerry_direction ;

    //Constructor of ControllerThread
    JerryController(Tuple positionDepart){
        //Get all the threads
        Jerry_Pos = new Tuple(positionDepart.Row,positionDepart.Col);
        jerry_direction = 5;    //no direction
    }

    //Important part :
    public void run() {
        //test
        while(true){
            //check collision before move
            CheckCollision();
            move(jerry_direction);
            DeleteOldPos();
            //check collision after move
            CheckCollision();
            pauser();
        }
    }
    //check collision
    private void CheckCollision(){
        if((Jerry_Pos.Row == PlayGUI.Exit[1] && Jerry_Pos.Col == PlayGUI.Exit[0]) || (TomController.Tom_Pos.Row == JerryController.Jerry_Pos.Row && TomController.Tom_Pos.Col== JerryController.Jerry_Pos.Col)){
            stopTheGame();      //Exit or Caught
        }
    }

    //delay between each move of Jerry
    private void pauser(){
        try {
            sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();}
    }

    //Stops The Game
    private void stopTheGame(){
        if(Jerry_Pos.Row == PlayGUI.Exit[1] && Jerry_Pos.Col == PlayGUI.Exit[0]) {
            System.out.println("Jerry Arrives Exit Point! You Win!\n");
        }
        while(true){
            pauser();
        }
    }

    //Moves Jerry and refreshes the positions in the arraylist
    //1:right 2:left 3:top 4:bottom 0:nothing
    protected void move(int dir){
        switch(dir){
            case 4:
                if(Jerry_Pos.Row < 29) {
                    if(PlayGUI.Grid.get(Jerry_Pos.Row + 1).get(Jerry_Pos.Col).typeOfVertex != 1) {
                        Jerry_Pos.ChangeData(Jerry_Pos.Row + 1, Jerry_Pos.Col);
                    }
                }
                break;
            case 3:
                if(Jerry_Pos.Row > 0) {
                    if(PlayGUI.Grid.get(Jerry_Pos.Row - 1).get(Jerry_Pos.Col).typeOfVertex != 1) {
                        Jerry_Pos.ChangeData(Jerry_Pos.Row - 1, Jerry_Pos.Col);
                    }
                }
                break;
            case 2:
                if(Jerry_Pos.Col > 0) {
                    if(PlayGUI.Grid.get(Jerry_Pos.Row).get(Jerry_Pos.Col - 1).typeOfVertex != 1) {
                        Jerry_Pos.ChangeData(Jerry_Pos.Row, Jerry_Pos.Col - 1);
                    }
                }
                break;
            case 1:
                if(Jerry_Pos.Col < 29) {
                    if(PlayGUI.Grid.get(Jerry_Pos.Row).get(Jerry_Pos.Col + 1).typeOfVertex != 1) {
                        Jerry_Pos.ChangeData(Jerry_Pos.Row, Jerry_Pos.Col + 1);
                    }
                }
                break;
            default: 	break;
        }
    }

    //Refresh the squares that needs to be
    private void DeleteOldPos(){
        if(Jerry_Pos.Row != Jerry_Pos.Old_Row || Jerry_Pos.Col != Jerry_Pos.Old_Col) {
            //add color to new position
            PlayGUI.Grid.get(Jerry_Pos.Row).get(Jerry_Pos.Col).square.ChangeColor(Color.orange);
            //delete color of old position
            if(Jerry_Pos.Old_Row == PlayGUI.Entry[1] && Jerry_Pos.Old_Col == PlayGUI.Entry[0]){
                PlayGUI.Grid.get(Jerry_Pos.Old_Row).get(Jerry_Pos.Old_Col).square.ChangeColor(Color.red);
            }
            else{
                PlayGUI.Grid.get(Jerry_Pos.Old_Row).get(Jerry_Pos.Old_Col).square.ChangeColor(Color.white);
            }
        }
    }
}
