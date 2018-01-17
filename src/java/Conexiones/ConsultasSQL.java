/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import DBlunarLander.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dam2a07
 */
public class ConsultasSQL {

    DBConnection ms = new DBConnection();
    Connection conn = ms.doConnection();

    public boolean comprobarRegUser(Connection conn, String username) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM users WHERE username = ? ;";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        rs.next();

        int numAux = rs.getInt("total");

        if (numAux == 0) {
            return true;
        }
        return false;
    }

    public void registrarUser(Connection conn, String nombre, String username, String password) throws SQLException {
        String sql = "INSERT into users (nombre,username,password) VALUES (?,?,?);";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nombre);
        pst.setString(2, username);
        pst.setString(3, password);
        pst.execute();
    }

    public Users GetUserById(Connection conn, String nameC) throws SQLException {
        String sql = "SELECT id,nombre,username,password FROM users WHERE id = id";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nameC);
        ResultSet rs = pst.executeQuery();

        int id = rs.getInt("id");
        String name = rs.getString("nombre");
        String un = rs.getString("username");
        String pwd = rs.getString("password");
        Users userAux = new Users(id, name, un, pwd);
        return userAux;
    }

    public boolean comprobarLogUser(Connection conn, String username) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM users WHERE username = ?;";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        rs.next();

        int numAux = rs.getInt("total");

        if (numAux == 1) {
            return true;
        }
        return false;
    }

    public Users GetUser(Connection conn, String username) throws SQLException {
        String sql = "SELECT id,nombre,username,password FROM users WHERE username = ?;";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        rs.next();

        int id = rs.getInt("id");
        String name = rs.getString("nombre");
        String un = rs.getString("username");
        String pwd = rs.getString("password");
        Users userAux = new Users(id, name, un, pwd);

        return userAux;
    }

}
