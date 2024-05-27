package id.tayi.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private StringProperty username;
    private StringProperty password;
    private StringProperty kota;
    private StringProperty nomor;
    private IntegerProperty points;
    
    public User(String username, String password, String kota, String nomor,
            int points) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.kota = new SimpleStringProperty(kota);
        this.nomor = new SimpleStringProperty(nomor);
        this.points = new SimpleIntegerProperty(points);
    }
    public User(String username, String password, String kota, String nomor) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.kota = new SimpleStringProperty(kota);
        this.nomor = new SimpleStringProperty(nomor);
    }
    public StringProperty getUsername() {
        return username;
    }
    public void setUsername(StringProperty username) {
        this.username = username;
    }
    public StringProperty getPassword() {
        return password;
    }
    public void setPassword(StringProperty password) {
        this.password = password;
    }
    public StringProperty getKota() {
        return kota;
    }
    public void setKota(StringProperty kota) {
        this.kota = kota;
    }
    public StringProperty getNomor() {
        return nomor;
    }
    public void setNomor(StringProperty nomor) {
        this.nomor = nomor;
    }
    public IntegerProperty getPoints() {
        return points;
    }
    public void setPoints(IntegerProperty points) {
        this.points = points;
    }

}
