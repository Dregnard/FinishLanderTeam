/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuraciones;

import DBlunarLander.Configuration;
import DBlunarLander.ConfigurationJpaController;
import DBlunarLander.UserJpaController;
import DBlunarLander.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dam2a07
 */
@WebServlet(name = "Guardar", urlPatterns = {"/Guardar"})
public class Guardar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LanderTeamPU");
            UserJpaController uc = new UserJpaController(emf);

            ConfigurationJpaController cc = new ConfigurationJpaController(emf);
            Users u = uc.findUserByUsername(request.getParameter("username"));

            if (u == null) {
                Map<String, String> emess = new HashMap<>();
                emess.put("error", "Usuario no encontrado");

                Gson gson = new GsonBuilder().create();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println(gson.toJson(emess));
            } else {
                if (u != null) {

                    Configuration conf = new Configuration();
                    conf.setDifId(Integer.parseInt(request.getParameter("dificultad")));
                    conf.setLunaId(Integer.parseInt(request.getParameter("modeloLuna")));
                    conf.setNaveId(Integer.parseInt(request.getParameter("modeloNave")));
                    conf.setUserId(u.getId());
                    cc.create(conf);

                    Map<String, String> mess = new HashMap<>();
                    mess.put("mess", "Configuration añadida");

                    Gson gson = new GsonBuilder().create();

                    response.setContentType("application/json");
                    PrintWriter pw = response.getWriter();
                    pw.println(gson.toJson(mess));
                } else {
                    Map<String, String> emess = new HashMap<>();
                    emess.put("error", "Error al guardar configuraciones");

                    Gson gson = new GsonBuilder().create();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.setContentType("application/json");
                    PrintWriter pw = response.getWriter();
                    pw.println(gson.toJson(emess));
                }
            }

        } catch (Exception e) {
            Map<String, String> emess = new HashMap<>();
            emess.put("error", "Error en configuraciones");

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
