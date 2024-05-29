package id.tayi.view;

import id.tayi.model.MainPage;
import id.tayi.model.RootPage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RewardPage implements MainPage, RootPage{
    private StackPane content;

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
        nama.getStyleClass().add("nama");

        // nama.textProperty().bind(UserController.user.getUsername());
        // poin.textProperty().bind(UserController.user.getPoints().asString());
        // info.setAlignment(Pos.TOP_LEFT);
        StackPane.setAlignment(info, Pos.TOP_LEFT);
    }

    public StackPane getContent() {
        return content;
    }

}
