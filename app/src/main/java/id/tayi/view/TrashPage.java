package id.tayi.view;

import id.tayi.App;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TrashPage {
    private App app;
    private Scene scene;
    private DoubleProperty h = new SimpleDoubleProperty(0);

    public TrashPage(App app) {
        this.app = app;
        initialize();
    }

    public void initialize() {
        Button kg = new Button("Kg");
        Label header = new Label("Buang Sampah");
        Button submit = new Button("Submit");
        Button kembali = new Button("Kembali");
        TextField nilai = new TextField();
        TextField alamat = new TextField();
        ComboBox<String> jenis = new ComboBox<>();
        CheckBox nanya = new CheckBox("Diantarkan ke bank sampah?");
        HBox bawah = new HBox(160, kembali, submit);
        HBox berat = new HBox(10,nilai, kg);
        VBox input = new VBox(20, header, jenis, berat, alamat, nanya, bawah);
        StackPane root = new StackPane(input);
        scene = new Scene(root, 1366, 693);

        jenis.getItems().addAll("Kertas", "Plastik", "Kaca", "Besi", "Organik", "Elektronik");

        input.setId("input");
        header.setId("header");
        kg.getStyleClass().add("btn");
        root.setId("background");
        submit.getStyleClass().add("btn");
        kembali.getStyleClass().add("btn");
        nilai.setPromptText("Berat");
        nilai.getStyleClass().add("field");
        jenis.getStyleClass().add("field");
        alamat.getStyleClass().add("field");
        alamat.setPromptText("Alamat");
        jenis.setPromptText("Jenis sampah");
        nanya.getStyleClass().add("custom-checkbox");
        input.setAlignment(Pos.CENTER);
        berat.setAlignment(Pos.CENTER);
        bawah.setAlignment(Pos.CENTER);

        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*\\.?\\d*")) {
                return change;
            }
            return null;
        });
        nilai.setTextFormatter(textFormatter);

        kembali.setOnAction(e ->{
            app.showHomePage();
        });

        submit.setOnAction(e ->{
            app.showHistoryPage();
        });

        scene.getStylesheets().add("/style/trash.css");
    }

    public Scene getScene() {
        return scene;
    }
}