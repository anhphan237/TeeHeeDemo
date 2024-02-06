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
                String sql = "select * from \n"
                        + "(SELECT \n"
                        + "    [customerID],\n"
                        + "    [email],\n"
                        + "    [password],\n"
                        + "    [firstname],\n"
                        + "    [lastname],\n"
                        + "    [phone],\n"
                        + "    [point],\n"
                        + "    [member],\n"
                        + "    [img],\n"
                        + "    [status]\n"
                        + "FROM \n"
                        + "    [dbo].[Customer]\n"
                        + "UNION\n"
                        + "SELECT \n"
                        + "    [staffID] as [customerID],\n"
                        + "    [email],\n"
                        + "    [password],\n"
                        + "    [firstname],\n"
                        + "    [lastname],\n"
                        + "    [phone],\n"
                        + "    0 as [point],  \n"
                        + "    '' as [member], \n"
                        + "    [img],\n"
                        + "    [status]\n"
                        + "FROM \n"
                        + "    [dbo].[Staff]) a\n"
                        + "WHERE \n"
                        + "[email] = ?\n"
                        + "AND [password] = ?";
                //3. create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result   
                if (rs.next()) {
                    String customerId = rs.getString("CustomerID");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String phone = rs.getString("phone");
                    int point = rs.getInt("point");
                    String member = rs.getString("member");
                    String img = rs.getString("img");
                    boolean status = rs.getBoolean("status");
                    if (status) {
                        acc = new CustomerDTO(customerId, email, password, firstName,
                                lastName, phone, point, member, img, status);
                    }
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

    public String checkUserRole(String email, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        String result = "";

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select * from\n"
                        + "(select [customerID],[email],[password],[firstname],"
                        + "[lastname],[phone],[img],[status]\n"
                        + "from [dbo].[Customer]\n"
                        + "union \n"
                        + "select \n"
                        + "[staffID] as [customerID],[email],[password],"
                        + "[firstname],[lastname],[phone],[img],[status]\n"
                        + "from [dbo].[Staff]) a\n"
                        + "where a.email = ?\n"
                        + "and a.password = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String customerId = rs.getString("CustomerID");
                    if (customerId.toUpperCase().contains("ST")) {
                        result = "1";
                    } else if (customerId.toUpperCase().contains("C")) {
                        result = "2";
                    }
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
        return result;
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
            String sql = "SELECT [customerID]\n"
                    + "      ,[email]\n"
                    + "      ,[password]\n"
                    + "      ,[firstname]\n"
                    + "      ,[lastname]\n"
                    + "      ,[phone]\n"
                    + "      ,[point]\n"
                    + "      ,[member]\n"
                    + "      ,[img]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Customer]";

            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerID"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"),
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

                c = new CustomerDTO(password, email, password, firstName,
                        lastName, phone, point, member, img, status);
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

                stm.setString(1, s.getCustomerId());
                stm.setString(2, s.getEmail());
                stm.setString(3, s.getPassword());
                stm.setString(4, s.getFirstName());
                stm.setString(5, s.getLastName());
                stm.setString(6, s.getPhone());
                stm.setInt(7, s.getPoint());
                stm.setString(8, s.getMember());
                stm.setString(9, s.getImg());
                stm.setBoolean(10, s.isStatus());
                stm.setString(11, s.getCustomerId());

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

    public boolean updateUserProfile(String email, String firstName, String lastName, String phone, String img, String customerId)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Customer]\n"
                        + "   SET [email] = ?\n"
                        + "      ,[firstName] = ?\n"
                        + "      ,[lastName] = ?\n"
                        + "      ,[phone] = ?\n"
                        + "      ,[img] = ?\n"
                        + " WHERE [customerID] = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, email);
                stm.setString(2, firstName);
                stm.setString(3, lastName);
                stm.setString(4, phone);
                stm.setString(5, img);
                stm.setString(6, customerId);

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
    
    public boolean updatePassword(String password, String customerId)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Customer]\n"
                        + "   SET [password] = ?\n"
                        + " WHERE [customerID] = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, password);
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
    
    public CustomerDTO searchCustomerById(String customerId) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [customerID]\n"
                    + "      ,[email]\n"
                    + "      ,[password]\n"
                    + "      ,[firstname]\n"
                    + "      ,[lastname]\n"
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
                CustomerDTO c = new CustomerDTO(rs.getString("customerId"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"),
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
    
    public ArrayList<CustomerDTO> searchCustomerByName(String firstName) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<CustomerDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [customerID]\n"
                    + "      ,[email]\n"
                    + "      ,[password]\n"
                    + "      ,[firstname]\n"
                    + "      ,[lastname]\n"
                    + "      ,[phone]\n"
                    + "      ,[point]\n"
                    + "      ,[member]\n"
                    + "      ,[img]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Customer]\n"
                    + "  WHERE [firstname] like ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" +firstName + "%");
            rs = st.executeQuery();
            while (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerId"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"),
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
    
    public ArrayList<CustomerDTO> searchCustomerByPhone(String phone) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<CustomerDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [customerID]\n"
                    + "      ,[email]\n"
                    + "      ,[password]\n"
                    + "      ,[firstname]\n"
                    + "      ,[lastname]\n"
                    + "      ,[phone]\n"
                    + "      ,[point]\n"
                    + "      ,[member]\n"
                    + "      ,[img]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Customer]\n"
                    + "  WHERE [phone] = ?";
            st = con.prepareStatement(sql);
            st.setString(1, phone);
            rs = st.executeQuery();
            while (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerId"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"),
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
    
    public ArrayList<CustomerDTO> searchCustomerByEmail(String email) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<CustomerDTO> list = new ArrayList<>();

        try {
            con = DBHelper.makeConnection();
            String sql = "select * from customer "
                    + "where email like ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%"+email+"%");
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
    
    public CustomerDTO searchCustomerByEmail_2(String email) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<CustomerDTO> list = new ArrayList<>();

        try {
            con = DBHelper.makeConnection();
            String sql = "select * from customer "
                    + "where email like ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%"+email+"%");
            rs = st.executeQuery();
            while (rs.next()) {
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
}
