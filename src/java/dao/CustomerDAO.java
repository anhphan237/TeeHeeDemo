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
                    acc = new CustomerDTO(customerID, email, password, firstName, lastName, phone, point, member,img, status);
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
    
    public ArrayList<CustomerDTO> getAll() throws ClassNotFoundException, SQLException{
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
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"),rs.getString("img"),
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
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"),rs.getString("img"),
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
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"),rs.getString("img"),
                        rs.getBoolean("status"));
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void insertCustomer(String email, String firstName, String lastName, String password, String phone) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDTO acc = null;

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
                stm.setInt(7, 0);
                stm.setString(8, "Basic");
                stm.setString(9, "CC_C3-scaled-e1695366218592");
                stm.setBoolean(10, true);

                stm.executeUpdate();
            }
        } catch (Exception e) {
        }
    }

    public String createCustomerId() {
        int count = 0;
        try {
            String sql = "SELECT [CustomerID]\n"
                    + "  FROM [dbo].[Customer] ";
            Connection con = DBHelper.makeConnection();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
        }
        return "" + count;
    }

}
