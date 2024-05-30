package id.tayi.view;

import id.tayi.controller.UserController;
import id.tayi.model.MainPage;
import id.tayi.model.RootPage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RewardPage implements MainPage, RootPage{
    private StackPane content;

    public RewardPage() {
        initialize();
    }

    public void initialize() {
        Label nama = new Label();
        Label poin = new Label();
        VBox info = new VBox(nama, poin);
        GridPane grid = new GridPane(10, 10);

        grid.add(new RewardPoint("10000", "Pulsa"),  0, 0);
        grid.add(new RewardPoint("50000", "Voucher"),  1, 0);
        grid.add(new RewardPoint("Apalah", "Listrik"),  0, 1);
        grid.add(new RewardPoint("despacito", "Bank"),  1, 1);
        grid.add(new RewardPoint("Apalah", "E-Wallet"),  2, 0);
        grid.add(new RewardPoint("despacito", "GoPay"),  2, 1);

        content = new StackPane(info, grid);

        poin.setId("poin");
        info.setId("info");
        nama.getStyleClass().add("nama");

        nama.textProperty().bind(UserController.user.getUsername());
        poin.textProperty().bind(UserController.user.getPoints().asString("%d Pts"));
        info.setAlignment(Pos.TOP_LEFT);
        grid.setAlignment(Pos.BOTTOM_CENTER);

        StackPane.setAlignment(info, Pos.TOP_LEFT);
    }

    public StackPane getContent() {
        return content;
    }

}
