package id.tayi.view;

import id.tayi.App;
import id.tayi.controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.util.Optional;

public class HomePage {
    private App app;
    private Scene scene;

    public HomePage(App app) {
        this.app = app;
        initialize();
    }

    public void initialize() {
        Label uang = new Label("Uang");
        Label nama = new Label("Hi, ");
        Label desc = new Label(
                "Bersama BuangKit kita bangkitkan budaya buang sampah\npada tempatnya. Bisa hasilin uang loh...");
        Label buang = new Label("Buang");
        Label bangkit = new Label("Bangkit");
        ImageView logo = new ImageView(new Image(getClass().getResource("/images/logo4.png").toExternalForm()));
        VBox trio = new VBox(-40, buang, uang, bangkit);
        HBox duo = new HBox(logo, trio);
        Button home = new Button("Beranda");
        Button start = new Button("Start!");
        Button tukar = new Button("Tukar");
        Button logout = new Button("Logout");
        Button riwayat = new Button("Riwayat");
        HBox bar = new HBox(10, home, riwayat, tukar, logout);
        StackPane root = new StackPane(bar, duo, desc, nama, start);
        StackPane.setMargin(desc, new Insets(300, 0, 0, -650));
        StackPane.setAlignment(nama, Pos.TOP_LEFT);
        StackPane.setMargin(start, new Insets(300, 0, 0, 900));
        scene = new Scene(root, 1366, 693);

        home.setId("selected");

        duo.setId("duo");
        bar.setId("bar");
        desc.setId("desc");
        logo.setId("logo");
        start.setId("start");
        logo.setFitWidth(190);
        logo.setFitHeight(190);
        root.setId("background");
        bar.setAlignment(Pos.TOP_RIGHT);
        nama.getStyleClass().add("nama");
        duo.setAlignment(Pos.CENTER_LEFT);
        trio.setAlignment(Pos.CENTER_LEFT);
        home.getStyleClass().add("togle");
        tukar.getStyleClass().add("togle");
        uang.getStyleClass().add("header");
        buang.getStyleClass().add("header");
        logout.getStyleClass().add("togle");
        bangkit.getStyleClass().add("header");
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

        riwayat.setOnAction(e -> {
            app.showHistoryPage();
        });

        tukar.setOnAction(e -> {
            app.showRewardPage();
        });

        if (UserController.user != null) {
            nama.setText(nama.getText() + UserController.user.getUsername().getValue());
        } else {
            nama.setText(nama.getText() + "guest");
        }

        start.setOnAction(e -> {
            app.showTrashPage();
        });

        scene.getStylesheets().add("/style/home.css");
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50, 100, 50, 100));
    }

    public Scene getScene() {
        return scene;
    }

    public void defaultOn() {

    }
}
