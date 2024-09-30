package MainManu;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainManuApplicationTest {

    @Test
    void TestMain() {
        MainManuApplication.main(new String[]{});
    }

    @Test
    void TestStart() throws IOException {
        MainManuApplication mainManuApplication = new MainManuApplication();
        mainManuApplication.start(new Stage());
    }
}