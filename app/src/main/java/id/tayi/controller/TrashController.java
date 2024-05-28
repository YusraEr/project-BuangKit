package id.tayi.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import id.tayi.config.DatabaseConnector;
import id.tayi.model.Trash;

public class TrashController {
    public void addTrash(Trash trash) {
        String sql = "INSERT INTO countedTrash(username, type, berat, lokasi, waktu) VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseConnector.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, trash.getusername().getValue());
            pstmt.setString(2, trash.getType().getValue());
            pstmt.setDouble(3, trash.getBerat().getValue());
            pstmt.setString(4, trash.getAlamat().getValue());
            pstmt.setString(5, trash.getWaktu().getValue());
            pstmt.executeUpdate();
            System.out.println("Trash added successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Trash> getTrashByUsername(String username) {
        String sql = "SELECT id, username, type, berat, lokasi, waktu FROM countedTrash WHERE username = ?";
        ArrayList<Trash> trashList = new ArrayList<>();

        try (Connection conn = DatabaseConnector.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Trash trash = new Trash(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("type"),
                        rs.getDouble("berat"),
                        rs.getString("lokasi"),
                        rs.getString("waktu"));
                trashList.add(trash);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return trashList;
    }
}
