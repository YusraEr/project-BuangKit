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

public class RewardPage {
    private App app;
    private Scene scene;
    private StackPane root;

    public RewardPage(App app) {
        this.app = app;
        initialize();
        mainScene();
    }

    public void initialize() {
        Button home = new Button("Beranda");
        Button tukar = new Button("Tukar");
        Button logout = new Button("Logout");
        Button riwayat = new Button("Riwayat");
        HBox bar = new HBox(10, home, riwayat, tukar, logout);
        root = new StackPane(bar);
        scene = new Scene(root, 1366, 693);

        tukar.setId("selected");

        bar.setId("bar");
        bar.setAlignment(Pos.TOP_RIGHT);
        home.getStyleClass().add("togle");
        logout.getStyleClass().add("togle");
        tukar.getStyleClass().add("togle");
        riwayat.getStyleClass().add("togle");

        logout.setOnAction(e -> {
            Alert log = new Alert(AlertType.CONFIRMATION);
            log.setTitle("Konfirmasi Logout");
            log.setHeaderText(null);
            log.setContentText("Apakah anda yakin ingin logout dari akun anda?");

            Optional<ButtonType> result = log.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK)
                app.showLoginPage();
        });

        home.setOnAction(e -> {
            app.showHomePage();
        });

        riwayat.setOnAction(e -> {
            app.showHistoryPage();
        });

        scene.getStylesheets().add("/style/home.css");
        root.setAlignment(Pos.CENTER);
        root.setId("background");
    }

    public Scene getScene() {
        return scene;
    }

    public void mainScene() {

    }
}
