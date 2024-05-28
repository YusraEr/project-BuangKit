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
    private StackPane content;

    public HomePage(App app) {
        this.app = app;
        initialize();
    }
    public HomePage() {
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
        Button start = new Button("Start!");
        content = new StackPane(duo, desc, nama, start);
        StackPane.setMargin(desc, new Insets(300, 0, 0, -650));
        StackPane.setAlignment(nama, Pos.TOP_LEFT);
        StackPane.setMargin(start, new Insets(300, 0, 0, 900));

        duo.setId("duo");
        desc.setId("desc");
        logo.setId("logo");
        start.setId("start");
        logo.setFitWidth(190);
        logo.setFitHeight(190);
        nama.getStyleClass().add("nama");
        duo.setAlignment(Pos.CENTER_LEFT);
        trio.setAlignment(Pos.CENTER_LEFT);
        uang.getStyleClass().add("header");
        buang.getStyleClass().add("header");
        bangkit.getStyleClass().add("header");

        if (UserController.user != null) {
            nama.setText(nama.getText() + UserController.user.getUsername().getValue());
        } else {
            nama.setText(nama.getText() + "guest");
        }
        content.setAlignment(Pos.CENTER);
    }

    public StackPane getContent() {
        return content;
    }

    public void defaultOn() {

    }
}
