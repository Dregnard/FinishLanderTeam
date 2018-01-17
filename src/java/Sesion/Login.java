/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Conexiones.ConsultasSQL;
import Conexiones.DBConnection;
import DBlunarLander.UserJpaController;
import DBlunarLander.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LanderTeamPU");
            UserJpaController uc = new UserJpaController(emf);

            Users user = null;

            if (((user = uc.findUser(username)) != null) && (user.getPassword().equals(password))) {
                
                Cookie usernameCookie = new Cookie("uSC", username);
                Cookie pwdCookie = new Cookie("pC", password);

                usernameCookie.setMaxAge(60 * 60 * 24 * 365);
                pwdCookie.setMaxAge(60 * 60 * 24 * 365);

                usernameCookie.setPath("/");
                pwdCookie.setPath("/");

                response.addCookie(usernameCookie);
                response.addCookie(pwdCookie);

                RequestDispatcher a = request.getRequestDispatcher("game.jsp");
                a.forward(request, response);

            } else {
                Map<String, String> emess = new HashMap<>();
                emess.put("error", "Usuario y/o contraseña incorrectos");

                Gson gson = new GsonBuilder().create();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println(gson.toJson(emess));
            }

        } catch (Exception e) {
            Map<String, String> emess = new HashMap<>();
            emess.put("error", "Error en el login");

            Gson gson = new GsonBuilder().create();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(emess));
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
