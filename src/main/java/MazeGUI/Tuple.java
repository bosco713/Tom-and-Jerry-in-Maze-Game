package MazeGUI;

public class Tuple {
    protected int Row;
    protected int Col;
    protected int Old_Row;
    protected int Old_Col;

    public Tuple(int row, int col) {
        this.Row = row;
        this.Col = col;
        this.Old_Row = row;
        this.Old_Col = col;
    }
    public void ChangeData(int row, int col){
        this.Old_Row = this.Row;
        this.Old_Col = this.Col;
        this.Row = row;
        this.Col = col;
    }
}