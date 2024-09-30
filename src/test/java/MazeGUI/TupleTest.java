package MazeGUI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

    @Test
    void changeDataTest() {
        Tuple tester = new Tuple(0,0);
        tester.ChangeData(1,1);
        int Expected_Row = 1;
        int Expected_Col = 1;
        int Expected_Old_Row = 0;
        int Expected_Old_Col = 0;
        assertEquals(Expected_Row, tester.Row);
        assertEquals(Expected_Col, tester.Col);
        assertEquals(Expected_Old_Row, tester.Old_Row);
        assertEquals(Expected_Old_Col, tester.Old_Col);
    }
}