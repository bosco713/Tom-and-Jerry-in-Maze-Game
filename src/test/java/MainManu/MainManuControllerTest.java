package MainManu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainManuControllerTest {

    @Test
    void TestOnFunctionAButtonClick() {
        MainManuController mainManuController = new MainManuController();
        mainManuController.onFunctionAButtonClick();        // target function
    }

    @Test
    void TestOnFunctionBButtonClick() {
        MainManuController mainManuController = new MainManuController();
        mainManuController.onFunctionBButtonClick();        // target function
    }

    @Test
    void TestOnFunctionCButtonClick() {
        MainManuController mainManuController = new MainManuController();
        mainManuController.onFunctionCButtonClick();        // target function
    }
}