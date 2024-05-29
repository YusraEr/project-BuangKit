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
    StringProperty username = new SimpleStringProperty("Tidak ada data");
    StringProperty type = new SimpleStringProperty("Tidak ada data");
    DoubleProperty berat = new SimpleDoubleProperty(0.0);
    StringProperty alamat = new SimpleStringProperty("Tidak ada data");
    StringProperty waktu = new SimpleStringProperty("Tidak ada data");
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
        VBox detail = new VBox();
        ScrollPane list = new ScrollPane(daftar);
        HBox main = new HBox(10, detail, list);
        content = new StackPane(info, main);

        Label username = new Label();
        username.textProperty().bind(this.username);
        username.setAlignment(Pos.CENTER);
        Label type = new Label();
        type.textProperty().bind(this.type);
        type.setAlignment(Pos.CENTER);
        Label berat = new Label();
        berat.textProperty().bind(this.berat.asString("%.1f Kg"));
        berat.setAlignment(Pos.CENTER);
        Label alamat = new Label();
        alamat.textProperty().bind(this.alamat);
        alamat.setAlignment(Pos.CENTER);
        Label waktu = new Label();
        waktu.textProperty().bind(this.waktu);
        waktu.setAlignment(Pos.CENTER);

        type.getStyleClass().add("field");
        berat.getStyleClass().add("field");
        waktu.getStyleClass().add("field");
        alamat.getStyleClass().add("field");
        username.getStyleClass().add("field");
        detail.getChildren().addAll(username, type, berat, alamat, waktu);

        poin.setId("poin");
        info.setId("info");
        nama.getStyleClass().add("nama");
        detail.getStyleClass().add("isi");
        nama.textProperty().bind(UserController.user.getUsername());
        poin.textProperty().bind(UserController.user.getPoints().asString());
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
