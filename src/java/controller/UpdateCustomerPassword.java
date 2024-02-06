/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CustomerDAO;
import dto.CustomerDTO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class UpdateCustomerPassword extends HttpServlet {

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
        String id = request.getParameter("CustomerId");
        CustomerDAO dao = new CustomerDAO();
        try {
            CustomerDTO c = dao.searchCustomerById(id);
            request.setAttribute("customer", c);
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        } finally {
            request.getRequestDispatcher("user_view/user_profile_change_password.jsp").forward(request, response);
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
        String id = request.getParameter("customerId");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        CustomerDAO dao = new CustomerDAO();

        try {
            CustomerDTO c = dao.searchCustomerById(id);
            if (c == null) {
                // Handle case where customer does not exist
                request.setAttribute("error", "Customer not found!");
                // Forward to an appropriate error page or display a message
            } else {
                String password = c.getPassword();
                if (!password.equals(oldPassword)) {
                    request.setAttribute("error", "Wrong password!!!");
                } else if (newPassword.equals(password)) {
                    request.setAttribute("error", "Password is not valid!!!");
                } else if (!newPassword.equals(confirmPassword)) {
                    request.setAttribute("error", "New Password and Confirm Password do not match!!!");
                }
                dao.updatePassword(newPassword, id);
                request.setAttribute("success", "Password updated successfully!");
                response.sendRedirect("CustomerList");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
//            response.sendRedirect("CustomerList");
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
