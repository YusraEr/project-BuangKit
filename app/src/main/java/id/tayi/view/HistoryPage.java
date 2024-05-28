package id.tayi.view;

import java.util.Optional;

import id.tayi.App;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class HistoryPage {
    private App app;
    private Scene scene;
    private StackPane content;

    public HistoryPage(App app) {
        this.app = app;
        initialize();
    }

    public void initialize() {
        content = new StackPane();
        content.setAlignment(Pos.CENTER);
    }

    public Scene getScene() {
        return scene;
    }

    public StackPane getContent() {
        return content;
    }
}
