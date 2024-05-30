package id.tayi.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ContentDetail extends HBox{
    private String info;
    private Label label;
    

    ContentDetail(String info, Label label){
        this.label = label;
        this.info = info;
        initialize();
    }

    public void initialize(){
        this.setSpacing(20);

        Label nama = new Label(info + " :");
        nama.setAlignment(Pos.CENTER_LEFT);
        label.setAlignment(Pos.CENTER_LEFT);
        
        label.getStyleClass().add("field");
        nama.getStyleClass().add("detail-info");
        
        this.getChildren().addAll(nama, label);
        this.setAlignment(Pos.CENTER);
        this.setPrefSize(100, 60);
    }
}
