module Comp3111F23G47 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens MainManu to javafx.fxml;
    exports MainManu;
}