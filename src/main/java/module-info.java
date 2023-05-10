module com.example.mygame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.mygame to javafx.fxml;
    exports com.example.mygame;
}