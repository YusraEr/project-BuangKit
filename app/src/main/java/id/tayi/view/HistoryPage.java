package id.tayi.view;

import java.util.ArrayList;

import id.tayi.App;
import id.tayi.controller.TrashController;
import id.tayi.controller.UserController;
import id.tayi.model.MainPage;
import id.tayi.model.RootPage;
import id.tayi.model.Trash;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HistoryPage implements MainPage, RootPage {
    private StackPane content;
    private App app;
    private TrashController trashDAO = new TrashController();
    StringProperty username = new SimpleStringProperty("-");
    StringProperty type = new SimpleStringProperty("-");
    DoubleProperty berat = new SimpleDoubleProperty(0.0);
    StringProperty alamat = new SimpleStringProperty("-");
    StringProperty waktu = new SimpleStringProperty("-");
    ArrayList<Trash> isi;

    public HistoryPage(App app) {
        this.isi = trashDAO.getTrashByUsername(UserController.user.getUsername().getValue());
        this.app = app;
        initialize();
    }

    public void initialize() {
        Label nama = new Label();
        Label poin = new Label();
        Label judul = new Label("Detail Transaksi");
        Button buang = new Button("+");
        VBox daftar = new VBox(5);
        ImageView coin = new ImageView(new Image(getClass().getResource("/images/coin.png").toExternalForm()));
        HBox info2 = new HBox(10, coin, poin);
        VBox info = new VBox(10, nama, info2);
        VBox detail = new VBox(-10);
        ScrollPane list = new ScrollPane(daftar);
        HBox main = new HBox(10, detail, list);
        content = new StackPane(info, main, buang);

        Label type = new Label();
        Label berat = new Label();
        Label waktu = new Label();
        Label alamat = new Label();
        Label username = new Label();

        type.textProperty().bind(this.type);
        waktu.textProperty().bind(this.waktu);
        berat.textProperty().bind(this.berat.asString("%.1f Kg"));
        alamat.textProperty().bind(this.alamat);
        username.textProperty().bind(this.username);

        detail.getChildren().addAll(judul,
                new ContentDetail("Username", username),
                new ContentDetail("Jenis", type),
                new ContentDetail("Waktu", waktu),
                new ContentDetail("Berat", berat),
                new ContentDetail("Lokasi", alamat));

        poin.setId("poin");
        info.setId("info");
        judul.setId("header");
        nama.getStyleClass().add("nama");
        buang.getStyleClass().add("buang");
        list.getStyleClass().add("isi");
        detail.getStyleClass().add("isi");
        list.setMaxSize(430, 530);
        coin.setFitWidth(40);
        coin.setFitHeight(40);
        list.setContent(daftar);
        info2.setAlignment(Pos.CENTER_LEFT);
        nama.textProperty().bind(UserController.user.getUsername());
        poin.textProperty().bind(UserController.user.getPoints().asString("%,d Pts"));

        if (isi != null) {
            for (Trash i : isi) {
                daftar.getChildren().add(new ListContent(i, this));
            }
        } else {
            daftar.getChildren().add(new Label("Tidak ada Riwayat"));
        }
        buang.setOnAction(e -> {
            app.showTrashPage();
        });

        StackPane.setAlignment(info, Pos.TOP_LEFT);
        StackPane.setAlignment(buang, Pos.BOTTOM_RIGHT);
        main.setAlignment(Pos.BOTTOM_CENTER);
        StackPane.setAlignment(main, Pos.BOTTOM_CENTER);
    }

    public StackPane getContent() {
        return content;
    }

    public void ganti(String username, String type, double weight, String addres, String time) {
        this.username.setValue(username);
        this.type.setValue(type);
        this.berat.setValue(weight);
        this.alamat.setValue(addres);
        this.waktu.setValue(time);

        Platform.runLater(() -> {
            content.requestLayout();
        });
    }

}
