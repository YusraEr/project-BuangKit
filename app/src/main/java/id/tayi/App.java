package id.tayi;

import id.tayi.view.DefaultPage;
import id.tayi.view.LoginPage;
import id.tayi.view.RegisterPage;
import id.tayi.view.TrashPage;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("BuangKit");
        stage.getIcons().add(new Image(getClass().getResource("/images/logo2.png").toExternalForm()));

        showLoginPage();
    }

    public void showLoginPage() {
        LoginPage login = new LoginPage(this);
        stage.setScene(login.getScene());
        stage.show();
    }

    public void showRegisterPage() {
        RegisterPage regis = new RegisterPage(this);
        stage.setScene(regis.getScene());
        stage.show();
    }

    public void showTrashPage() {
        TrashPage his = new TrashPage(this);
        stage.setScene(his.getScene());
        stage.show();
    }

    public void showDefaultPage() {
        DefaultPage his = new DefaultPage(this);
        stage.setScene(his.getScene());
        stage.show();
    }
}