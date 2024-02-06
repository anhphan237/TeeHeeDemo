/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.TransactionDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBHelper;

/**
 *
 * @author Admin
 */
public class TransactionDAO {

//    public ArrayList<TransactionDTO> getAll() throws ClassNotFoundException, SQLException {
//        ArrayList<TransactionDTO> list = new ArrayList();
//        Connection con = null;
//        PreparedStatement st = null;
//        ResultSet rs = null;
//        OrderDAO oDAO = new OrderDAO();
//
//        try {
//            con = DBHelper.makeConnection();
//            String sql = "SELECT [transactionID]\n"
//                    + "      ,[createdTime]\n"
//                    + "      ,[value]\n"
//                    + "      ,[orderID]\n"
//                    + "  FROM [dbo].[Transaction]";
//
//            st = con.prepareStatement(sql);
//            rs = st.executeQuery();
//            while (rs.next()) {
//                TransactionDTO c = new TransactionDTO(rs.getString("transactionId"), rs.getDate("createdTime"),
//                        rs.getDouble("value"), oDAO.searchOrderById(rs.getString("orderId")));
//                list.add(c);
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (st != null) {
//                st.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return list;
//    }

    public boolean insertTransaction(Date createdTime, double value, String orderId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        String transactionId = createTransactionId();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Transaction]\n"
                        + "           ([transactionID]\n"
                        + "           ,[createdTime]\n"
                        + "           ,[value]\n"
                        + "           ,[orderID])"
                        + "     VALUES (?,?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, transactionId);
                stm.setDate(2, createdTime);
                stm.setDouble(3, value);
                stm.setString(4, orderId);

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

    public String createTransactionId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newTransactionId = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from [Transaction]";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newTransactionId = String.format("T%06d", count + 1);
                }
            }
            if (newTransactionId != null) {
                return newTransactionId;
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

//    public TransactionDTO searchTransactionById(String transactionId) throws ClassNotFoundException, SQLException {
//        Connection con = null;
//        ResultSet rs = null;
//        PreparedStatement stm = null;
//        OrderDAO oDAO = new OrderDAO();
//
//        try {
//            con = DBHelper.makeConnection();
//            if (con != null) {
//                String sql = "SELECT [transactionID]\n"
//                        + "      ,[createdTime]\n"
//                        + "      ,[value]\n"
//                        + "      ,[orderID]\n"
//                        + "  FROM [dbo].[Transaction]"
//                        + "  where [transactionID] = ?";
//
//                stm = con.prepareStatement(sql);
//                stm.setString(1, transactionId);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    TransactionDTO b = new TransactionDTO(transactionId, rs.getDate("createdTime"), rs.getDouble("value"),
//                            oDAO.searchOrderById(rs.getString("orderId")));
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

    public boolean updateTransaction(TransactionDTO t)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Transaction]\n"
                        + "   SET [transactionID] = ?\n"
                        + "      ,[createdTime] = ?\n"
                        + "      ,[value] = ?\n"
                        + "      ,[orderID] = ?"
                        + " WHERE [transactionID] = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, t.getTransactionId());
                stm.setDate(2, t.getCreatedTime());
                stm.setDouble(3, t.getValue());
                stm.setString(4, t.getOrder().getOrderId());
                stm.setString(5, t.getTransactionId());

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
