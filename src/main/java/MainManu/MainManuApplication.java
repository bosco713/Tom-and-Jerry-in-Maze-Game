package MainManu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;

public class MainManuApplication extends Application {
    /**
     * MainManuApplication is used to create window objects
     * @author          mhyuenac
     * @param args      Argument when calling main
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start build up the information of the window
     * @author                  mhyuenac
     * @param primaryStage      The stage building the window
     * @throws IOException      Throw exception
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        URL fxmlLocation = MainManuApplication.class.getResource("/fxml/MainManu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load(), 572, 448);
        primaryStage.setTitle("COMP3111_Project_Group47");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}
