package id.tayi.view;

import id.tayi.App;
import id.tayi.controller.UserController;
import id.tayi.model.MainPage;
import id.tayi.model.RootPage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RewardPage implements MainPage, RootPage {
    private StackPane content;
    private App app;

    public RewardPage(App app) {
        this.app = app;
        initialize();
    }

    public void initialize() {
        Label nama = new Label();
        Label poin = new Label();
        Button buang = new Button("+");
        ImageView coin = new ImageView(new Image(getClass().getResource("/images/coin.png").toExternalForm()));
        HBox info2 = new HBox(10, coin, poin);
        VBox info = new VBox(10, nama, info2);

        
        GridPane grid = new GridPane(10, 10);

        grid.add(new RewardPoint("min. 10k Pts", "Pulsa"), 0, 0);
        grid.add(new RewardPoint("min. 50k Pts", "Voucher"), 1, 0);
        grid.add(new RewardPoint("min. 100k Pts", "Listrik"), 0, 1);
        grid.add(new RewardPoint("min. 100k Pts", "Bank"), 1, 1);
        grid.add(new RewardPoint("min. 50k Pts", "E-Wallet"), 2, 0);
        grid.add(new RewardPoint("unlimited Pts", "More"), 2, 1);

        content = new StackPane(info, grid, buang);

        poin.setId("poin");
        info.setId("info");
        nama.getStyleClass().add("nama");
        buang.getStyleClass().add("buang");

        nama.textProperty().bind(UserController.user.getUsername());
        poin.textProperty().bind(UserController.user.getPoints().asString("%,d Pts"));
        info.setAlignment(Pos.TOP_LEFT);
        grid.setAlignment(Pos.BOTTOM_CENTER);
        coin.setFitWidth(40);
        coin.setFitHeight(40);
        info2.setAlignment(Pos.CENTER_LEFT);

        buang.setOnAction(e -> {
            app.showTrashPage();
        });

        StackPane.setAlignment(info, Pos.TOP_LEFT);
        StackPane.setAlignment(buang, Pos.BOTTOM_RIGHT);
    }

    public StackPane getContent() {
        return content;
    }

}
