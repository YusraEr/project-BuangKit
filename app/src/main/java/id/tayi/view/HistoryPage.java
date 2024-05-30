package id.tayi.view;

import java.util.ArrayList;

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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HistoryPage implements MainPage, RootPage {
    private StackPane content;
    private TrashController trashDAO = new TrashController();
    StringProperty username = new SimpleStringProperty("-");
    StringProperty type = new SimpleStringProperty("-");
    DoubleProperty berat = new SimpleDoubleProperty(0.0);
    StringProperty alamat = new SimpleStringProperty("-");
    StringProperty waktu = new SimpleStringProperty("-");
    ArrayList<Trash> isi;

    public HistoryPage() {
        this.isi = trashDAO.getTrashByUsername(UserController.user.getUsername().getValue());
        initialize();
    }

    public void initialize() {
        Label nama = new Label();
        Label poin = new Label();
        VBox daftar = new VBox(5);
        VBox info = new VBox(nama, poin);
        VBox detail = new VBox(-10);
        ScrollPane list = new ScrollPane(daftar);
        HBox main = new HBox(10, detail, list);
        content = new StackPane(info, main);

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

        detail.getChildren().addAll(
                new ContentDetail("Username", username),
                new ContentDetail("Jenis", type),
                new ContentDetail("Waktu", waktu),
                new ContentDetail("Berat", berat),
                new ContentDetail("Alamat", alamat)
        );

        poin.setId("poin");
        info.setId("info");
        nama.getStyleClass().add("nama");
        detail.getStyleClass().add("isi");
        list.getStyleClass().add("isi");
        nama.textProperty().bind(UserController.user.getUsername());
        poin.textProperty().bind(UserController.user.getPoints().asString("%d Pts"));
        list.setMaxSize(430, 530);
        list.setContent(daftar);

        if (isi != null) {
            for (Trash i : isi) {
                daftar.getChildren().add(new ListContent(i, this));
            }
        } else {
            daftar.getChildren().add(new Label("Tidak ada Riwayat"));
        }

        StackPane.setAlignment(info, Pos.TOP_LEFT);
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
