package id.tayi.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trash {
    private int id;
    private StringProperty username;
    private StringProperty type;
    private DoubleProperty berat;
    private StringProperty alamat;
    private StringProperty waktu;

    public Trash(String username, String type, double berat, String alamat,
            String waktu) {
        this.username = new SimpleStringProperty(username);
        this.type = new SimpleStringProperty(type);
        this.berat = new SimpleDoubleProperty(berat);
        this.alamat = new SimpleStringProperty(alamat);
        this.waktu = new SimpleStringProperty(waktu);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StringProperty getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat.setValue(alamat);
    }

    public StringProperty getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu.setValue(waktu);
    }

    public StringProperty getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username.setValue(username);
    }

    public StringProperty getType() {
        return type;
    }

    public void setType(String type) {
        this.type.setValue(type);
    }

    public DoubleProperty getBerat() {
        return berat;
    }

    public void setBerat(double berat) {
        this.berat.setValue(berat);
    }
}
