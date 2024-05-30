package id.tayi.view;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class PopUp extends Application {

    public static void LoadingPopUp(Stage stage) {
        Stage loadingStage = new Stage();
        loadingStage.initModality(Modality.APPLICATION_MODAL);
        loadingStage.initStyle(StageStyle.UNDECORATED);
        loadingStage.initOwner(stage);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        StackPane root = new StackPane(progressIndicator);
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        root.setPrefSize(100, 100);
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(root);
        loadingStage.setScene(scene);

        loadingStage.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> loadingStage.hide());
        pause.play();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Main Application");

        LoadingPopUp(stage);

        StackPane mainLayout = new StackPane();
        Scene mainScene = new Scene(mainLayout, 300, 200);
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
