package id.tayi.view;

import id.tayi.App;
import id.tayi.controller.UserController;
import id.tayi.model.MainPage;
import id.tayi.model.RootPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomePage implements MainPage, RootPage{
    private StackPane content;
    private App app;

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
        VBox trio = new VBox(-20, buang, uang, bangkit);
        HBox duo = new HBox(logo, trio);
        Button start = new Button("Start!");
        content = new StackPane(duo, desc, nama, start);
        StackPane.setMargin(desc, new Insets(300, 0, 0, -650));
        StackPane.setAlignment(nama, Pos.TOP_LEFT);
        StackPane.setMargin(start, new Insets(300, 0, 0, 900));

        duo.setId("duo");
        desc.setId("desc");
        logo.setId("logo");
        logo.setFitWidth(190);
        logo.setFitHeight(190);
        duo.setAlignment(Pos.CENTER_LEFT);
        trio.setAlignment(Pos.CENTER_LEFT);
        nama.getStyleClass().add("nama");
        uang.getStyleClass().add("header");
        start.getStyleClass().add("start");
        buang.getStyleClass().add("header");
        bangkit.getStyleClass().add("header");

        if (UserController.user != null) {
            nama.setText(nama.getText() + UserController.user.getUsername().getValue());
        } else {
            nama.setText(nama.getText() + "guest");
        }
        buang.setOnMouseClicked(e ->{
            app.showTrashPage();
        });
        content.setAlignment(Pos.CENTER);
    }

    public StackPane getContent() {
        return content;
    }

    public void defaultOn() {

    }
}
