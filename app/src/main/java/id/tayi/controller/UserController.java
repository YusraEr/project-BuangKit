package id.tayi.controller;

import id.tayi.config.DatabaseConnector;
import id.tayi.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    public static User user;

    public void addUser(User user) {
        String sql = "INSERT INTO users(username, password, nomor, kota) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnector.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername().getValue());
            pstmt.setString(2, user.getPassword().getValue());
            pstmt.setString(3, user.getNomor().getValue());
            pstmt.setString(4, user.getKota().getValue());
            pstmt.executeUpdate();
            System.out.println("User added successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String checkLogin(String username, String password) {
        String checkUserSql = "SELECT COUNT(*) FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnector.connect();
                PreparedStatement checkUserStmt = conn.prepareStatement(checkUserSql)) {
            checkUserStmt.setString(1, username);

            ResultSet userRs = checkUserStmt.executeQuery();
            if (userRs.next() && userRs.getInt(1) == 0) {
                return "Username tidak ditemukan.";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }

        String checkPasswordSql = "SELECT username, password, points, kota, nomor FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnector.connect();
                PreparedStatement checkPasswordStmt = conn.prepareStatement(checkPasswordSql)) {
            checkPasswordStmt.setString(1, username);
            checkPasswordStmt.setString(2, password);

            ResultSet passwordRs = checkPasswordStmt.executeQuery();
            if (passwordRs.next()) {
                String Username = passwordRs.getString("username");
                String Password = passwordRs.getString("password");
                int poin = passwordRs.getInt("points");
                String nomor = passwordRs.getString("nomor");
                String kota = passwordRs.getString("kota");
                UserController.user = new User(Username, Password, kota, nomor, poin);
                return "done";
            } else {
                return "Password salah.";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnector.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
