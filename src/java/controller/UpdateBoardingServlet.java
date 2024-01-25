/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BoardingDAO;
import dto.BoardingDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class UpdateBoardingServlet extends HttpServlet {

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
        String id = request.getParameter("BoardingId");
        BoardingDAO dao = new BoardingDAO();

        try {
            BoardingDTO b = dao.searchBoardingById(id);
            request.setAttribute("boarding", b);

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        } finally {
            request.getRequestDispatcher("updateBoarding.jsp").forward(request, response);
        }
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
        String boardingId = request.getParameter("id");
        String name = request.getParameter("name");
        String rate_raw = request.getParameter("rate");
        String description = request.getParameter("description");
        String img = request.getParameter("img");
        String length_raw = request.getParameter("length");
        String height_raw = request.getParameter("height");
        String width_raw = request.getParameter("width");
        String maxWeight_raw = request.getParameter("maxWeight");

        try {
            double rate = Double.parseDouble(rate_raw);
            double length = Double.parseDouble(length_raw);
            double height = Double.parseDouble(height_raw);
            double width = Double.parseDouble(width_raw);
            double maxWeght = Double.parseDouble(maxWeight_raw);

            BoardingDAO dao = new BoardingDAO();
            BoardingDTO s = new BoardingDTO(boardingId, name, rate, description, img, length, height, width, maxWeght, true);
            boolean result = dao.updateBoarding(s);
            
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        } finally {
            response.sendRedirect("BoardingServlet");
        }
    }
}
