/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Puntuaciones;

import DBlunarLander.Configuration;
import DBlunarLander.ConfigurationJpaController;
import DBlunarLander.Score;
import DBlunarLander.ScoreJpaController;
import DBlunarLander.UserJpaController;
import DBlunarLander.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "GuardarScore", urlPatterns = {"/GuardarScore"})
public class GuardarScore extends HttpServlet {

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

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LanderTeamPU");
            ConfigurationJpaController cc = new ConfigurationJpaController(emf);

            ScoreJpaController sc = new ScoreJpaController(emf);

            Score core = new Score();
            core.setConfId(Integer.parseInt(request.getParameter("conf_id")));
            core.setSpeed(Float.parseFloat(request.getParameter("speed")));
            core.setFuel(Float.parseFloat(request.getParameter("fuel")));

            SimpleDateFormat formatter5 = new SimpleDateFormat("mm:ss");
            core.setIniTime(formatter5.parse(request.getParameter("iniTime")));
            core.setEndTime(formatter5.parse(request.getParameter("endTime")));

            
            sc.create(core);

            Map<String, String> mess = new HashMap<>();
            mess.put("mess", "Puntuacion añadida");

            Gson gson = new GsonBuilder().create();

            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(mess));

        } catch (Exception e) {
            Map<String, String> emess = new HashMap<>();
            emess.put("error", "Error GuardarScore");

            Gson gson = new GsonBuilder().create();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(emess));

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
