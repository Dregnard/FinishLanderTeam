/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Conexiones.ConsultasSQL;
import Conexiones.DBConnection;
import DBlunarLander.Users;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dam2a07
 */
@WebServlet(name = "AutoLogin", urlPatterns = {"/AutoLogin"})
public class AutoLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        RequestDispatcher rd = request.getRequestDispatcher("/index.html");
        rd.forward(request, response);

        String cookieName = "uCid";
        String cookieUsername = "uSC";
        String cookiePass = "pC";

        Cookie[] cookies = request.getCookies();

        String nameC = null;
        String usernameC = null;
        String passwordC = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookieName.equals(cookie.getName())) {
                    usernameC = cookie.getValue();
                }
                if (cookieUsername.equals(cookie.getName())) {
                    nameC = cookie.getValue();
                }
                if (cookiePass.equals(cookie.getName())) {
                    passwordC = cookie.getValue();
                }
            }
            if (nameC == null || usernameC == null || passwordC == null) {
                RequestDispatcher a = request.getRequestDispatcher("index.html");
                a.forward(request, response);
            } else {
                DBConnection ms = new DBConnection();
                Connection conn = ms.doConnection();
                try {
                    ConsultasSQL cons = new ConsultasSQL();
                    Users user = cons.GetUserById(conn, nameC);
                    if (user.getName().equals(nameC) && user.getPassword().equals(passwordC)) {

                        request.setAttribute("Nombre", user);
                        RequestDispatcher a = request.getRequestDispatcher("game.jsp");
                        a.forward(request, response);

                    } else {
                        RequestDispatcher a = request.getRequestDispatcher("index.html");
                        a.forward(request, response);

                    }
                } catch (IOException | ServletException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(AutoLogin.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    ms.closeConnection();
                }
            }

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
