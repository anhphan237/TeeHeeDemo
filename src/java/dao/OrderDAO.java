/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.OrderDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import utils.DBHelper;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    public ArrayList<OrderDTO> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<OrderDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        CustomerDAO cDAO = new CustomerDAO();
        VoucherDAO vDAO = new VoucherDAO();
        OrderBoardingDetailDAO obDAO = new OrderBoardingDetailDAO();
        OrderServiceDetailDAO osDAO = new OrderServiceDetailDAO();

        try {
            con = DBHelper.makeConnection();
            String sql = "select a.*,b.roomID,b.petID,b.checkInDate,b.checkOutDate,c.petID,c.serviceID \n"
                    + "from [dbo].[Order] a, [dbo].[Order_Boarding_Detail] b, [dbo].[Order_Service_Detail] c\n"
                    + "where a.OrderID = b.orderID and b.orderID = c.orderID\n"
                    + "order by [status], [createdDate] desc";

            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                OrderDTO o = new OrderDTO(rs.getString("orderID"), rs.getTimestamp("createdDate"),
                        rs.getInt("status"), rs.getDouble("total"), vDAO.searchVoucherById(rs.getString("voucherId")),
                        cDAO.searchCustomerById(rs.getString("customerId")), obDAO.getOrderBoardingDetailById(rs.getString("orderID")),
                        osDAO.getOrderBoardingDetailById(rs.getString("orderID")));
                list.add(o);
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

    public boolean insertOrder(Timestamp createdDate, int status, double total, String voucherId, String customerId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        String orderId = createOrderId();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Order]\n"
                        + "           ([OrderID]\n"
                        + "           ,[createdDate]\n"
                        + "           ,[status]\n"
                        + "           ,[total]\n"
                        + "           ,[voucherID]\n"
                        + "           ,[customerID])"
                        + "     VALUES (?,?,?,?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, orderId);
                stm.setTimestamp(2, createdDate);
                stm.setInt(3, status);
                stm.setDouble(4, total);
                stm.setString(5, voucherId);
                stm.setString(6, customerId);

                rs = stm.executeUpdate();
                if (rs != 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public String createOrderId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newOrderId = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from [Order]";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newOrderId = String.format("O%06d", count + 1);
                }
            }
            if (newOrderId != null) {
                return newOrderId;
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

//    public OrderDTO searchOrderById(String orderId) throws ClassNotFoundException, SQLException {
//        Connection con = null;
//        ResultSet rs = null;
//        PreparedStatement stm = null;
//        VoucherDAO vDAO = new VoucherDAO();
//        CustomerDAO cDAO = new CustomerDAO();
//
//        try {
//            con = DBHelper.makeConnection();
//            if (con != null) {
//                String sql = "SELECT [OrderID]\n"
//                        + "      ,[createdDate]\n"
//                        + "      ,[status]\n"
//                        + "      ,[total]\n"
//                        + "      ,[voucherID]\n"
//                        + "      ,[customerID]\n"
//                        + "  FROM [dbo].[Order]"
//                        + "  where [OrderID] = ?";
//
//                stm = con.prepareStatement(sql);
//                stm.setString(1, orderId);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    OrderDTO b = new OrderDTO(orderId, rs.getTimestamp("createdDate"), rs.getInt("status"),
//                            rs.getDouble("total"), vDAO.searchVoucherById(rs.getString("voucherId")),
//                            cDAO.searchCustomerById(rs.getString("customerId")));
//                    if (b != null) {
//                        return b;
//                    }
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return null;
//    }
    public boolean updateOrder(OrderDTO o)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Order]\n"
                        + "   SET [createdDate] = ?\n"
                        + "      ,[status] = ?\n"
                        + "      ,[total] = ?\n"
                        + "      ,[voucherID] = ?\n"
                        + "      ,[customerID] = ?"
                        + " WHERE [orderID] = ?";

                stm = con.prepareStatement(sql);

                stm.setTimestamp(1, o.getCreatedDate());
                stm.setInt(2, o.getStatus());
                stm.setDouble(3, o.getTotal());
                stm.setString(4, o.getVoucher().getVoucherId());
                stm.setString(5, o.getCustomer().getCustomerId());
                stm.setString(6, o.getOrderId());

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

//    public ArrayList<OrderDTO> searchOrderByStatus(String status) throws ClassNotFoundException, SQLException {
//        Connection con = null;
//        ResultSet rs = null;
//        PreparedStatement stm = null;
//        VoucherDAO vDAO = new VoucherDAO();
//        CustomerDAO cDAO = new CustomerDAO();
//        ArrayList<OrderDTO> list = new ArrayList();
//
//        try {
//            con = DBHelper.makeConnection();
//            if (con != null) {
//                String sql = "SELECT [OrderID]\n"
//                        + "      ,[createdDate]\n"
//                        + "      ,[status]\n"
//                        + "      ,[total]\n"
//                        + "      ,[voucherID]\n"
//                        + "      ,[customerID]\n"
//                        + "  FROM [dbo].[Order]"
//                        + "  where [status] = ?\n"
//                        + "  order by [createdDate] desc";
//
//                stm = con.prepareStatement(sql);
//                stm.setString(1, status);
//                rs = stm.executeQuery();
//                while (rs.next()) {
//                    OrderDTO b = new OrderDTO(rs.getString("orderID"), rs.getTimestamp("createdDate"), rs.getInt("status"),
//                            rs.getDouble("total"), vDAO.searchVoucherById(rs.getString("voucherId")),
//                            cDAO.searchCustomerById(rs.getString("customerId")));
//                    if (b != null) {
//                        list.add(b);
//                    }
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return list;
//    }
}
