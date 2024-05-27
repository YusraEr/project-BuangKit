package id.tayi.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trash {
    private StringProperty id;
    private User user;
    private StringProperty type;
    private IntegerProperty berat;
    
    public StringProperty getId() {
        return id;
    }

    public void setId(StringProperty id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StringProperty getType() {
        return type;
    }

    public void setType(StringProperty type) {
        this.type = type;
    }

    public IntegerProperty getBerat() {
        return berat;
    }

    public void setBerat(IntegerProperty berat) {
        this.berat = berat;
    }

    public Trash(String id, User user, String type, int berat) {
        this.id = new SimpleStringProperty(id);
        this.user = user;
        this.type = new SimpleStringProperty(type);
        this.berat = new SimpleIntegerProperty(berat);
    }

}
