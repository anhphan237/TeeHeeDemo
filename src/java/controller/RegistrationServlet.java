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
import validate.RegistrationInsertErr;

/**
 *
 * @author Admin
 */
public class RegistrationServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            RegistrationInsertErr errors = new RegistrationInsertErr();
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String phone = request.getParameter("phone");
            CustomerDAO cDao = new CustomerDAO();
            String url = "registration.jsp";
            try {
                boolean error = false;
                if (email.isEmpty()) {
                    error = true;
                    errors.setEmailIsEmpty("Email must be filled!!!");
                }
                if (firstName.isEmpty()) {
                    error = true;
                    errors.setFirstNameIsEmpty("Firstname must be filled!!!");
                }
                if (lastName.isEmpty()) {
                    error = true;
                    errors.setLastNameIsEmpty("Lastname must be filled!!!");
                }
                if (password.isEmpty()) {
                    error = true;
                    errors.setPasswordIsEmpty("Password must be filled!!!");
                }
                if (cDao.isExisted(email)) {
                    error = true;
                    errors.setEmailIsExisted(email + " is existed!!!");
                }
                if (error) {
                    request.setAttribute("insertErr", errors);
                } else {
                    CustomerDTO c = cDao.searchCustomerByEmail_2(email);
                    if (c == null) {
                        cDao.insertCustomer(email, firstName, lastName, password, phone);
                        url = "login.jsp";
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    } else {
                        request.getRequestDispatcher(url).forward(request, response);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
                errors.setEmailIsExisted(email + " has already exist!");
                request.setAttribute("insertErr", errors);
            }
        }
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
