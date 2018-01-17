package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dam2a07
 */

//192.168.1.13
//172.16.6.157

public class DBConnection {
    private Connection conn = null;
    private final String url = "jdbc:mysql://192.168.1.13:3306/lunarLander";
    private final String user = "alumne";
    private final String password = "alualualu";

    public Connection doConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to " + conn.toString());
            }
            return conn;
        } catch (SQLException e) {
            System.out.println("Wrong sql_url, sql_username or sql_password");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection() {
        try {
            conn.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fallo al cerrar la conexión");
        }
    }

    public ResultSet exQuery(Connection c, String sql) {
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("ExecuteQuery ejecutado.");
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printRs(ResultSet rs) {
        try {
            while (rs.next()) {
                //System.out.println(rs.getString(1)+" - "+ rs.getString(2));
                System.out.println(rs.getString("codi") + " - " + rs.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exQueryPS(Connection c, String sql) {
        try {

            PreparedStatement prepstmt = c.prepareStatement(sql);
            prepstmt.executeUpdate();

            //Can't use ResultSet after close prepared statement, we have to print or fill object here...
            prepstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
