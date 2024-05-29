package id.tayi.view;

import java.util.Optional;

import id.tayi.App;
import id.tayi.model.MainPage;
import id.tayi.model.ScenePage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class DefaultPage implements MainPage, ScenePage{
    private App app;
    private Scene scene;
    private StackPane root;

    public DefaultPage(App app) {
        this.app = app;
        initialize();
    }

    public void initialize() {
        ToggleGroup nav = new ToggleGroup();
        RewardPage rPage = new RewardPage();
        HistoryPage hPage = new HistoryPage();
        ToggleButton home = new ToggleButton("Beranda");
        HomePage homePage = new HomePage();
        ToggleButton tukar = new ToggleButton("Tukar");
        ToggleButton logout = new ToggleButton("Logout");
        ToggleButton riwayat = new ToggleButton("Riwayat");
        HBox bar = new HBox(10, home, riwayat, tukar, logout);
        root = new StackPane();
        scene = new Scene(root, 1366, 693);

        // riwayat.setId("selected");

        bar.setId("bar");
        bar.setAlignment(Pos.TOP_RIGHT);
        home.getStyleClass().add("togle");
        logout.getStyleClass().add("togle");
        tukar.getStyleClass().add("togle");
        riwayat.getStyleClass().add("togle");

        logout.setToggleGroup(nav);
        home.setToggleGroup(nav);
        riwayat.setToggleGroup(nav);
        tukar.setToggleGroup(nav);
        logout.setUserData("Logout");
        home.setUserData("Home");
        riwayat.setUserData("Riwayat");
        tukar.setUserData("Tukar");
        


        nav.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    String selectedPage = newValue.getUserData().toString();
                    switch (selectedPage) {
                        case "Home":
                            root.getChildren().clear();
                            StackPane tmp = homePage.getContent();
                            root.getChildren().addAll(tmp, bar);
                            Button start = (Button) tmp.getChildren().get(3);
                            start.setOnAction(e -> {
                                app.showTrashPage();
                            });
                            break;
                        case "Riwayat":
                            root.getChildren().clear();
                            root.getChildren().addAll(hPage.getContent(), bar);
                            break;
                        case "Tukar":
                            root.getChildren().clear();
                            root.getChildren().addAll(rPage.getContent(), bar);
                            break;
                        case "Logout":
                            logout.setOnAction(e -> {
                                Alert log = new Alert(AlertType.CONFIRMATION);
                                log.setTitle("Konfirmasi Logout");
                                log.setHeaderText(null);
                                log.setContentText("Apakah anda yakin ingin logout dari akun anda?");
                                Optional<ButtonType> result = log.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK)
                                    app.showLoginPage();
                            });
                            System.out.println("ini log");
                            break;

                    }
                }
            }
        });
        nav.selectToggle(home);

        scene.getStylesheets().add("/style/home.css");
        StackPane.setAlignment(bar, Pos.TOP_RIGHT);
        root.setAlignment(Pos.CENTER);
        root.setId("background");
    }

    public Scene getScene() {
        return scene;
    }
}
