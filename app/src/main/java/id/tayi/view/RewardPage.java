package id.tayi.view;

import java.util.Optional;

import id.tayi.App;
import id.tayi.controller.UserController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RewardPage {
    private App app;
    private Scene scene;
    private StackPane content;

    public RewardPage(App app) {
        this.app = app;
        initialize();
    }
    public RewardPage() {
        initialize();
    }

    public void initialize() {
        Label nama = new Label("Yusra Erlangga Putra");
        Label poin = new Label("00000");
        VBox info = new VBox(nama, poin);
        content = new StackPane(info);

        poin.setId("poin");
        info.setId("info");
        nama.setId("nama");

        info.setAlignment(Pos.CENTER_LEFT);
        // nama.textProperty().bind(UserController.user.getUsername());
        // poin.textProperty().bind(UserController.user.getPoints().asString());

        content.setAlignment(Pos.CENTER);
        content.setId("background");
    }

    public StackPane getContent() {
        return content;
    }

}
