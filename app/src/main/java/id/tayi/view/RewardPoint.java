package id.tayi.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RewardPoint extends Button{
    private VBox content;
    private String nama;
    private String desc;
    RewardPoint(String nama, String desc){
        super();
        this.nama = nama;
        this.desc = desc;
        initialize();
    }
    public void initialize(){
        Label name = new Label(nama);
        Label desk = new Label(desc);
        content = new VBox();
        setGraphic(content);

        name.getStyleClass().add("name");
        desk.getStyleClass().add("desk");

        content.getChildren().addAll(name, desk);
        content.setAlignment(Pos.CENTER_LEFT);
        this.getStyleClass().add("meh");
        this.setOnAction(e ->{
            System.out.println("ini tombol");
        });
    }
}
