/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.UserDAO;
import model.User;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl() {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO tbl_user (username, address, phone, email, gender, profileImage) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getAddress());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getEmail());
            pstmt.setByte(5, user.getGender());
            pstmt.setString(6, user.getProfileImage());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(int id) {
        String sql = "SELECT * FROM tbl_user WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User.UserBuilder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setAddress(rs.getString("address"))
                        .setPhone(rs.getString("phone"))
                        .setEmail(rs.getString("email"))
                        .setGender(rs.getByte("gender"))
                        .setProfileImage(rs.getString("profileImage"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM tbl_user";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(new User.UserBuilder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setAddress(rs.getString("address"))
                        .setPhone(rs.getString("phone"))
                        .setEmail(rs.getString("email"))
                        .setGender(rs.getByte("gender"))
                        .setProfileImage(rs.getString("profileImage"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE tbl_user SET username = ?, address = ?, phone = ?, email = ?, gender = ?, profileImage = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getAddress());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getEmail());
            pstmt.setByte(5, user.getGender());
            pstmt.setString(6, user.getProfileImage());
            pstmt.setInt(7, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try (PreparedStatement st = connection.prepareStatement("DELETE FROM tbl_comment WHERE (user_comment_id = ?);")) {
            st.setInt(1, id);
            st.execute();
            System.out.println("Da xoa comment cua user");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM tbl_post WHERE (user_id = ?);"))
        {
            stm.setInt(1, id);
            stm.execute();
            System.out.println("Da xoa post cua user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "DELETE FROM tbl_user WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}