package id.tayi.view;

import id.tayi.App;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class TrashPage {
    private App app;
    private Scene scene;

    public TrashPage(App app) {
        this.app = app;
        initialize();
    }

    public void initialize() {
        ImageView kaca = new ImageView(new Image(getClass().getResource("/images/kaca.png").toExternalForm()));
        ImageView besi = new ImageView(new Image(getClass().getResource("/images/besi.png").toExternalForm()));
        ImageView kertas = new ImageView(new Image(getClass().getResource("/images/kertas.png").toExternalForm()));
        ImageView plastik = new ImageView(new Image(getClass().getResource("/images/plastik.png").toExternalForm()));
        ImageView organik = new ImageView(new Image(getClass().getResource("/images/organik.png").toExternalForm()));
        ImageView elektronik = new ImageView(new Image(getClass().getResource("/images/elektronik.png").toExternalForm()));

        GridPane choice = new GridPane(40, 40);
        StackPane root = new StackPane(choice);
        scene = new Scene(root, 1366, 693);

        kaca.setFitHeight(200);
        kaca.setFitWidth(150);

        pattern(kertas);
        pattern(plastik);
        pattern(besi);
        pattern(kaca);
        pattern(organik);
        pattern(elektronik);

        choice.add(kertas, 0, 0);
        choice.add(plastik, 1, 0);
        choice.add(besi, 2, 0);
        choice.add(kaca, 0, 1);
        choice.add(organik, 1, 1);
        choice.add(elektronik, 2, 1);

        scene.getStylesheets().add("/style/trash.css");
        kertas.setOnMousePressed(e -> {
            app.showHomePage();
        });
    }

    private static void pattern(ImageView image) {
        image.setFitHeight(200);
        image.setFitWidth(200);
        image.getStyleClass().add("image");
        image.setOnMouseEntered(e -> {

        });
        image.setOnMouseExited(e -> {

        });
    }

    public Scene getScene() {
        return scene;
    }
}