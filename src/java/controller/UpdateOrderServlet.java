/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.VoucherDAO;
import dto.OrderDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class UpdateOrderServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateOrderServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateOrderServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("OrderId");
        OrderDAO dao = new OrderDAO();
        try {
            OrderDTO o = dao.searchOrderById(id);
            request.setAttribute("order", o);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String createdDate_raw = request.getParameter("createdDate");
        String status_raw = request.getParameter("status");
        String total_raw = request.getParameter("total");
        String voucherId = request.getParameter("voucherId");
        String customerId = request.getParameter("customerId");

        try {
            double total = Double.parseDouble(total_raw);
            Date createdDate = Date.valueOf(createdDate_raw);
            int status = Integer.parseInt(status_raw);

            OrderDAO dao = new OrderDAO();
            VoucherDAO vDAO = new VoucherDAO();
            CustomerDAO cDAO = new CustomerDAO();
            OrderDTO o = new OrderDTO(orderId, createdDate, status, total, vDAO.searchVoucherById(voucherId), cDAO.searchCustomerById(customerId));
            boolean result = dao.updateOrder(o);
            
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        } finally {
            response.sendRedirect("OrderServlet");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
