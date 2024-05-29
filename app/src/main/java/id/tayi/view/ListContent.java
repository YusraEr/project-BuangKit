package id.tayi.view;

import id.tayi.model.Trash;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ListContent extends HBox {
    HistoryPage history;
    public int id;
    public String username;
    public String type;
    public double berat;
    public String alamat;
    public String waktu;

    public ListContent(Trash trash, HistoryPage history) {
        this.id = trash.getId();
        this.username = trash.getUsername().getValue();
        this.type = trash.getType().getValue();
        this.berat = trash.getBerat().getValue();
        this.alamat = trash.getAlamat().getValue();
        this.waktu = trash.getWaktu().getValue();

        Label jenis = new Label(trash.getType().getValue());
        Label weight = new Label(trash.getBerat().getValue().toString() + " Kg");
        Label time = new Label("Waktu : " + trash.getWaktu().getValue());
        Button i = new Button("i");

        jenis.setStyle("-fx-font-size: 15px; -fx-text-fill: #006e00; -fx-font-family: Arial; -fx-font-weight: bold;-fx-text-alignment: left; ");
        weight.setStyle("-fx-font-size: 15px; -fx-text-fill: black; -fx-font-family: Arial;");
        time.setStyle("-fx-font-size: 15px; -fx-text-fill: black; -fx-font-family: Arial;");
        
        this.setStyle("-fx-min-height: 40px; -fx-max-width: 400px; -fx-min-width: 420px; -fx-background-color: rgb(255, 255, 255);-fx-padding:5px;");
        this.getChildren().addAll(jenis, weight, time, i);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        i.setStyle("-fx-font-size: 15px; -fx-text-fill: white; -fx-font-family: Arial; -fx-background-color: #006e00; -fx-background-radius: 50px; -fx-font-weight: bold;");
        i.setAlignment(Pos.CENTER_RIGHT);
        i.setOnAction(e -> {
            history.ganti(username, type, berat, alamat, waktu);
            System.out.println(trash.getId());
        });
    }

}
