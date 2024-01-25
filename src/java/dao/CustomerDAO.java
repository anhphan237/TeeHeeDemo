/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.CustomerDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBHelper;

/**
 *
 * @author Admin
 */
public class CustomerDAO {

    public CustomerDTO checkLogin(String email, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDTO acc = null;
        try {
            //1. connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "select [CustomerID], [email], [password], firstName, lastName, phone, point, member, status, img "
                        + "FROM [dbo].[Customer] "
                        + "WHERE [email] = ? "
                        + "AND [password] = ?";
                //3. create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result   
                if (rs.next()) {
                    String customerID = rs.getString("CustomerID");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String phone = rs.getString("phone");
                    int point = rs.getInt("point");
                    String member = rs.getString("member");
                    String img = rs.getString("img");
                    boolean status = rs.getBoolean("status");
                    acc = new CustomerDTO(customerID, email, password, firstName, lastName, phone, point, member, img, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return acc;
    }

    public String createCustomerId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newCustomerId = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from Customer";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newCustomerId = String.format("C%06d", count + 1);
                }
            }
            if (newCustomerId != null) {
                return newCustomerId;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<CustomerDTO> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<CustomerDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "select * from customer ";

            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerId"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"), rs.getString("img"),
                        rs.getBoolean("status"));
                list.add(c);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public CustomerDTO searchCustomerByEmail(String email) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "select * from customer "
                    + "where email = ?";
            st = con.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerId"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"), rs.getString("img"),
                        rs.getBoolean("status"));
                return c;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean isExisted(String email) {
        try {
            Connection con = DBHelper.makeConnection();
            ArrayList<CustomerDTO> list = new ArrayList<>();
            String sql = "select email from customer ";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerId"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"), rs.getString("img"),
                        rs.getBoolean("status"));
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public CustomerDTO insertCustomer(String email, String firstName, String lastName, String password,
            String phone) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDTO acc = null;
        int point = 0;
        String member = "Basic";
        String img = "##";
        boolean status = true;
        CustomerDTO c = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Customer]\n"
                        + "           ([CustomerID]\n"
                        + "           ,[email]\n"
                        + "           ,[password]\n"
                        + "           ,[firstName]\n"
                        + "           ,[lastName]\n"
                        + "           ,[phone]\n"
                        + "           ,[point]\n"
                        + "           ,[member]\n"
                        + "           ,[img]\n"
                        + "           ,[status])\n"
                        + "    VALUES (?,?,?,?,?,?,?,?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, this.createCustomerId());
                stm.setString(2, email);
                stm.setString(3, password);
                stm.setString(4, firstName);
                stm.setString(5, lastName);
                stm.setString(6, phone);
                stm.setInt(7, point);
                stm.setString(8, member);
                stm.setString(9, img);
                stm.setBoolean(10, status);

                c = new CustomerDTO(password, email, password, firstName, lastName, phone, point, member, img, status);
                stm.executeUpdate();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return c;
    }

    public CustomerDTO searchCustomerById(String customerId) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [customerID]\n"
                    + "      ,[email]\n"
                    + "      ,[password]\n"
                    + "      ,[firstName]\n"
                    + "      ,[lastName]\n"
                    + "      ,[phone]\n"
                    + "      ,[point]\n"
                    + "      ,[member]\n"
                    + "      ,[img]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Customer]\n"
                    + "  WHERE [customerID] = ?";
            st = con.prepareStatement(sql);
            st.setString(1, customerId);
            rs = st.executeQuery();
            if (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerID"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"), rs.getString("img"),
                        rs.getBoolean("status"));
                return c;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean updateCustomer(CustomerDTO s)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Customer]\n"
                        + "   SET [customerID] = ?\n"
                        + "      ,[email] = ?\n"
                        + "      ,[password] = ?\n"
                        + "      ,[firstName] = ?\n"
                        + "      ,[lastName] = ?\n"
                        + "      ,[phone] = ?\n"
                        + "      ,[point] = ?\n"
                        + "      ,[member] = ?\n"
                        + "      ,[img] = ?\n"
                        + "      ,[status] = ?\n"
                        + " WHERE [customerID] = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, s.getCustomerID());
                stm.setString(2, s.getEmail());
                stm.setString(3, s.getPassword());
                stm.setString(4, s.getFirstName());
                stm.setString(5, s.getLastName());
                stm.setString(6, s.getPhone());
                stm.setInt(7, s.getPoint());
                stm.setString(8, s.getMember());
                stm.setString(9, s.getImg());
                stm.setBoolean(10, s.isStatus());
                stm.setString(11, s.getCustomerID());

                result = stm.executeUpdate();

                if (result != 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean deleteCustomer(String customerId)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;
        boolean status = false;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Customer]\n"
                        + "   SET [status] = ?\n"
                        + " WHERE [customerID] = ?";

                stm = con.prepareStatement(sql);

                stm.setBoolean(1, status);
                stm.setString(2, customerId);

                result = stm.executeUpdate();

                if (result != 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
