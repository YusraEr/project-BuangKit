package id.tayi.view;

import id.tayi.App;
import id.tayi.controller.UserController;
import id.tayi.model.MainPage;
import id.tayi.model.ScenePage;
import id.tayi.model.User;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RegisterPage implements MainPage, ScenePage{
    private App app;
    private Scene scene;
    private UserController userDAO = new UserController();

    public RegisterPage(App app) {
        this.app = app;
        initialize();
    }

    public void initialize() {
        Button go = new Button("Go!");
        Label plus = new Label("+62");
        Label info = new Label("Username sudah ada");
        Button back = new Button("Kembali");
        Label header = new Label("Daftar");
        TextField nomor = new TextField();
        TextField username = new TextField();
        TextField password = new TextField();
        ComboBox<String> kota = new ComboBox<>();
        HBox hb = new HBox(10, plus, nomor);
        VBox mid = new VBox(20, header, username, password, hb, kota, info, go);
        StackPane root = new StackPane(mid, back);
        StackPane.setAlignment(back, Pos.TOP_LEFT);
        scene = new Scene(root, 1366, 693);

        username.setPromptText("Username");
        password.setPromptText("Password");
        nomor.setPromptText("Nomor");
        kota.setPromptText("Pilih Kota");

        mid.setId("input");
        plus.setId("plus");
        back.setId("back");
        nomor.setId("nomor");
        header.setId("header");
        info.setVisible(false);
        root.setId("background");
        info.getStyleClass().add("info");
        kota.getStyleClass().add("field");
        username.getStyleClass().add("field");
        password.getStyleClass().add("field");

        kota.getItems().addAll(
                "Ambon", "Banda Aceh", "Bandar Lampung", "Bandung", "Banjarmasin", "Baubau",
                "Bekasi", "Bengkulu", "Bima", "Binjai", "Bitung", "Blitar", "Bogor", "Cimahi",
                "Cirebon", "Depok", "Denpasar", "Gorontalo", "Jakarta", "Jambi", "Jayapura",
                "Jember", "Kediri", "Kendari", "Ketapang", "Kotamobagu", "Kupang", "Lhokseumawe",
                "Madiun", "Magelang", "Makassar", "Malang", "Manado", "Mataram", "Medan", "Padang",
                "Padang Sidempuan", "Palangkaraya", "Palembang", "Palopo", "Palu", "Pangkal Pinang",
                "Parepare", "Pekanbaru", "Pontianak", "Probolinggo", "Samarinda", "Semarang",
                "Serang", "Sidoarjo", "Singkawang", "Solo", "Sorong", "Sukabumi", "Surabaya",
                "Tangerang", "Tangerang Selatan", "Tarakan", "Tebing Tinggi", "Tegal", "Ternate",
                "Tomohon", "Yogyakarta");
        go.setOnAction(e -> {
            String name = username.getText();
            String pass = password.getText();
            String nmr = nomor.getText();
            String kta = kota.getValue();
            if (name.isEmpty() || pass.isEmpty() || nmr.isEmpty() || kta.isEmpty()) {
                info.setText("Harap isi data diri anda");
                info.setVisible(true);
            } else if (userDAO.isUsernameExists(name)) {
                info.setText("Username sudah ada");
                info.setVisible(true);
            } else {
                Alert infoAlert = new Alert(AlertType.INFORMATION);
                infoAlert.setHeaderText(null);
                infoAlert.setContentText("Berhasil membuat akun");
                infoAlert.showAndWait();
                User user = new User(name, pass, nmr, kta);
                userDAO.addUser(user);
                app.showLoginPage();
            }
        });
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        });
        nomor.setTextFormatter(textFormatter);
        back.setOnAction(e -> {
            app.showLoginPage();
        });

        hb.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.CENTER);
        scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
    }

    public Scene getScene() {
        return this.scene;
    }
}
