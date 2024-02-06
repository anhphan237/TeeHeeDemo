///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package controller;
//
//import dao.OrderDAO;
//import dao.TransactionDAO;
//import dto.TransactionDTO;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.sql.Date;
//import java.sql.SQLException;
//
///**
// *
// * @author Admin
// */
//public class UpdateTransactionServlet extends HttpServlet {
//   
//    /** 
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UpdateTransactionServlet</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UpdateTransactionServlet at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    } 
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /** 
//     * Handles the HTTP <code>GET</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        String id = request.getParameter("TransactionId");
//        TransactionDAO dao = new TransactionDAO();
//        try {
//            
//            TransactionDTO t = dao.searchTransactionById(id);
//            request.setAttribute("transaction", t);
//            
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e){
//            e.printStackTrace();
//        }finally{
//            request.getRequestDispatcher("updateTransaction.jsp").forward(request, response);
//            //response.sendRedirect("TransactionServlet");
//        }
//    } 
//
//    /** 
//     * Handles the HTTP <code>POST</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        
//        String transactionId = request.getParameter("transactionId");
//        String createdTime_raw = request.getParameter("createdTime");
//        String value_raw = request.getParameter("value");
//        String orderId = request.getParameter("orderId");
//        OrderDAO oDAO = new OrderDAO();
//
//        try {
//            double value = Double.parseDouble(value_raw);
//            Date createdTime = Date.valueOf(createdTime_raw);
//            
//            TransactionDAO dao = new TransactionDAO();
//            TransactionDTO t = new TransactionDTO(transactionId, createdTime, value, 
//                    oDAO.searchOrderById(orderId));
//            boolean result = dao.updateTransaction(t);
//            
//        } catch (ClassNotFoundException ex) {
//
//        } catch (SQLException ex) {
//
//        } finally {
//            response.sendRedirect("TransactionServlet");
//        }
//    }
//
//    /** 
//     * Returns a short description of the servlet.
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
