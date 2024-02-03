
package controller;

import dao.PetDAO;
import dto.PetDTO;
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
public class UpdatePetServlet extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
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
        String id = request.getParameter("CustomerId");
        PetDAO dao = new PetDAO();

        try {
            PetDTO p = dao.searchPetById(id);
            request.setAttribute("pet", p);

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        } finally {
            request.getRequestDispatcher("updatePet.jsp").forward(request, response);
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
        String petId = request.getParameter("petId");
        String name = request.getParameter("name");
        String dob_raw = request.getParameter("dob");
        String customerId = request.getParameter("customerId");
        String type_raw = request.getParameter("type");
        String weight_raw = request.getParameter("weight");
        String gender_raw = request.getParameter("gender");
        String vaccinated_raw = request.getParameter("vaccinated");
        String status_raw = request.getParameter("status");

        try {
            double weight = Double.parseDouble(weight_raw);
            boolean status = Boolean.parseBoolean(status_raw);
            boolean type = Boolean.parseBoolean(type_raw);
            boolean gender = Boolean.parseBoolean(gender_raw);
            boolean vaccinated = Boolean.parseBoolean(vaccinated_raw);
            Date dob = Date.valueOf(dob_raw);

            PetDAO dao = new PetDAO();
            PetDTO p = new PetDTO(petId, name, dob, customerId, type, weight, gender, vaccinated, status);
            boolean result = dao.updatePet(p);
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        } finally {
            response.sendRedirect("PetServlet");
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
