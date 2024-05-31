package id.tayi.view;

import id.tayi.controller.UserController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RewardPoint extends Button {
    private UserController userDAO = new UserController();
    private VBox content;
    private String nama;
    private String desc;

    RewardPoint(String nama, String desc) {
        super();
        this.nama = nama;
        this.desc = desc;
        initialize();
    }

    public void initialize() {
        Label name = new Label(nama);
        Label desk = new Label(desc);
        content = new VBox();
        setGraphic(content);

        name.getStyleClass().add("name");
        desk.getStyleClass().add("desk");

        content.getChildren().addAll(name, desk);
        content.setAlignment(Pos.CENTER_LEFT);
        this.getStyleClass().add("meh");
        this.setOnAction(e -> {
            switch (desc) {
                case "Pulsa":
                    if (UserController.user.getPoints().getValue() >= 10000) {
                        popUpReward("Pulsa", "Provider", "Nomor Telepon", 50, "Telkomsel", "Indosat", "XL", "SmartFren",
                                "Tri");
                    } else {
                        Alert log = new Alert(AlertType.WARNING);
                        log.setTitle(null);
                        log.setTitle("Gagal memilih");
                        log.setHeaderText(null);
                        log.setContentText("Poin kamu tidak mencukupi");
                        log.showAndWait();
                    }
                    break;
                case "Voucher":
                    if (UserController.user.getPoints().getValue() >= 50000) {
                        popUpReward("Voucher", "Provider", "Nomor Telepon", 50, "Telkomsel", "Indosat", "XL",
                                "SmartFren", "Tri");
                    } else {
                        Alert log = new Alert(AlertType.WARNING);
                        log.setTitle(null);
                        log.setTitle("Gagal memilih");
                        log.setHeaderText(null);
                        log.setContentText("Poin kamu tidak mencukupi");
                        log.showAndWait();
                    }
                    break;
                case "Listrik":
                    if (UserController.user.getPoints().getValue() >= 100000) {
                        popUpReward("Listrik", "Jenis", "Nomor ID Pelanggan", 100, "Pasca-Bayar", "Pra-Bayar");
                    } else {
                        Alert log = new Alert(AlertType.WARNING);
                        log.setTitle(null);
                        log.setTitle("Gagal memilih");
                        log.setHeaderText(null);
                        log.setContentText("Poin kamu tidak mencukupi");
                        log.showAndWait();
                    }
                    break;
                case "Bank":
                    if (UserController.user.getPoints().getValue() >= 100000) {
                        popUpReward("Bank", "Jenis Bank", "No. Rekening", 100, "BRI", "BSI", "BCA", "Mandiri", "DBS",
                                "BankSulSelBar");
                    } else {
                        Alert log = new Alert(AlertType.WARNING);
                        log.setTitle(null);
                        log.setTitle("Gagal memilih");
                        log.setHeaderText(null);
                        log.setContentText("Poin kamu tidak mencukupi");
                        log.showAndWait();
                    }
                    break;
                case "E-Wallet":
                    if (UserController.user.getPoints().getValue() >= 50000) {
                        popUpReward("E-Wallet", "Jenis E-Wallet", "Nomor", 50, "GoPay", "DANA", "LinkAja", "OVO",
                                "ShopeePay");
                    } else {
                        Alert log = new Alert(AlertType.WARNING);
                        log.setTitle(null);
                        log.setTitle("Gagal memilih");
                        log.setHeaderText(null);
                        log.setContentText("Poin kamu tidak mencukupi");
                        log.showAndWait();
                    }
                    break;
                case "More":
                    popUpReward("Sumbangan", "Tujuan", "Nomor Telepon pengguna", 0, "Rumah Tahfiz", "All Eyes on Rafa",
                            "Panti Asuhan", "Korban Bencana Alam");
                    break;
            }
        });
    }

    private void popUpReward(String judul, String promptJudul, String promptNomor, int min, String... args) {
        Label head = new Label(judul);
        Stage stage = new Stage();
        Button submit = new Button("Submit");
        Button kembali = new Button("Kembali");
        TextField nomoran = new TextField();
        ComboBox<String> namaPilihan = new ComboBox<>();
        TextField poin = new TextField();
        HBox bawah = new HBox(150, kembali, submit);
        VBox vbox = new VBox(head, namaPilihan, nomoran, poin, bawah);
        Scene scene = new Scene(vbox, 450, 500);

        vbox.setId("input");
        head.setId("header");
        nomoran.setPromptText(promptNomor);
        namaPilihan.setPromptText(promptJudul);
        poin.setPromptText("Poin yang ditukar min. " + min + "");
        submit.getStyleClass().add("btn");
        kembali.getStyleClass().add("btn");
        nomoran.getStyleClass().add("field");
        namaPilihan.getStyleClass().add("field");
        poin.getStyleClass().add("field");
        bawah.setAlignment(Pos.CENTER);
        namaPilihan.getItems().addAll(args);

        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        });
        TextFormatter<String> textFormatter2 = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        });
        nomoran.setTextFormatter(textFormatter);
        poin.setTextFormatter(textFormatter2);

        scene.getStylesheets().add("/style/trash.css");
        kembali.setOnAction(e -> {
            stage.close();
        });

        submit.setOnAction(e -> {
            if (Integer.parseInt(poin.getText()) > UserController.user.getPoints().getValue()) {
                Alert log = new Alert(AlertType.WARNING);
                log.setTitle(null);
                log.setTitle("Gagal Submit");
                log.setHeaderText(null);
                log.setContentText("Poin kamu tidak mencukupi");
                log.showAndWait();
            } else if (namaPilihan.getValue() == null || poin.getText() == null || nomoran.getText() == null) {
                Alert log = new Alert(AlertType.WARNING);
                log.setTitle(null);
                log.setTitle("Gagal submit");
                log.setHeaderText(null);
                log.setContentText("Field tidak boleh kosong");
                log.showAndWait();
            } else if (Integer.parseInt(poin.getText()) < (min * 1000)) {
                Alert log = new Alert(AlertType.WARNING);
                log.setTitle(null);
                log.setTitle("Gagal submit");
                log.setHeaderText(null);
                log.setContentText("Poin yang ingin kamu gunakan kurang dari " + min + "k");
                log.showAndWait();
            } else {
                String nama = UserController.user.getUsername().getValue();
                int point = UserController.user.getPoints().getValue() - Integer.parseInt(poin.getText());
                userDAO.updateUserPoints(nama, point);
                UserController.user.setPoints(point);
                Alert log = new Alert(AlertType.INFORMATION);
                log.setTitle("Point reward");
                log.setHeaderText(null);
                log.setContentText("Berhasil ditukarkan");
                log.showAndWait();
                stage.close();
            }
        });
        stage.setTitle("Reward");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
