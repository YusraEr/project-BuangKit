package id.tayi.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.google.common.base.Optional;

import id.tayi.App;
import id.tayi.controller.TrashController;
import id.tayi.controller.UserController;
import id.tayi.model.MainPage;
import id.tayi.model.ScenePage;
import id.tayi.model.Trash;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TrashPage implements MainPage, ScenePage {
    private App app;
    private Scene scene;
    private TrashController trashDAO = new TrashController();
    private UserController userDAO = new UserController();

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
        CheckBox nanya = new CheckBox("Diantarkan ke bank sampah");
        HBox bawah = new HBox(160, kembali, submit);
        HBox berat = new HBox(10, nilai, kg);
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
        alamat.setPromptText("Lokasi sampah");
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

        kembali.setOnAction(e -> {
            app.showDefaultPage();
        });
        submit.setOnAction(e -> {
            String nama = UserController.user.getUsername().getValue();
            String type = jenis.getValue();
            double brt = Double.parseDouble(nilai.getText());
            String lokasi = alamat.getText();
            String waktu = timeNow();

            if (nanya.isSelected())
                lokasi = "Bank Sampah";

            // PopUp.LoadingPopUp(new Stage());
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.play();

            if (type != null && brt > 0 && (lokasi != null || lokasi == "Bank Sampah")) {
                Trash trash = new Trash(nama, type, brt, lokasi, waktu);
                Alert log = new Alert(AlertType.CONFIRMATION);
                log.setTitle("Status sampah");
                log.setHeaderText(null);
                log.setContentText("Sampah berhasil dilaporkan");
                
                if (lokasi == "Bank Sampah") {
                    int currentPoint = convertPoin(jenis.getValue(), brt) + UserController.user.getPoints().getValue();
                    UserController.user.setPoints(currentPoint);
                    userDAO.updateUserPoints(UserController.user.getUsername().getValue(), currentPoint);
                    log.setContentText("Sampah berhasil dilaporkan, anda mendapatkan " + currentPoint + " Pts");
                }
                
                log.showAndWait();
                reset(nilai, alamat);
                trashDAO.addTrash(trash);
                app.showDefaultPage();
            } else {
                reset(nilai, alamat);
                Alert log = new Alert(AlertType.CONFIRMATION);
                log.setTitle("Status sampah");
                log.setHeaderText(null);
                log.setContentText("Sampah gagal dilaporkan");
                log.showAndWait();

            }

        });
        nanya.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                alamat.setVisible(false);
            } else {
                alamat.setVisible(true);
            }
        });

        scene.getStylesheets().add("/style/trash.css");
    }

    public Scene getScene() {
        return scene;
    }

    public String timeNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
        String waktu = now.format(format);
        return waktu;
    }

    public int convertPoin(String type, double berat) {
        int poin = 0;
        Random random = new Random();

        switch (type) {
            case "Kertas":
                poin = 1000 + random.nextInt(2001);
                break;
            case "Plastik":
                poin = 500 + random.nextInt(2501);
                break;
            case "Besi":
                poin = 1000 + random.nextInt(4001);
                break;
            case "Kaca":
                poin = 500 + random.nextInt(1001);
                break;
            case "Organik":
                poin = 200 + random.nextInt(801);
                break;
            case "Elektronik":
                poin = 5000 + random.nextInt(15001);
                break;
        }
        poin *= berat;
        int hasil = (int) poin;
        return hasil;
    }

    private void reset(TextField... args) {
        for (TextField i : args) {
            i.clear();
            ;
        }
    }
}