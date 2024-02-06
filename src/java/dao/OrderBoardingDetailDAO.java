/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.OrderBoardingDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBHelper;

/**
 *
 * @author Admin
 */
public class OrderBoardingDetailDAO {

    public OrderBoardingDetailDTO getOrderBoardingDetailById(String orderId) throws SQLException, ClassNotFoundException {
        OrderBoardingDetailDTO result = null;

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        RoomDAO rDAO = new RoomDAO();
        PetDAO pDAO = new PetDAO();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [orderID]\n"
                        + "      ,[roomID]\n"
                        + "      ,[petID]\n"
                        + "      ,[checkInDate]\n"
                        + "      ,[checkOutDate]\n"
                        + "  FROM [dbo].[Order_Boarding_Detail]"
                        + "  where [OrderID] = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, orderId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    OrderBoardingDetailDTO b = new OrderBoardingDetailDTO(orderId, rDAO.searchRoomById(rs.getString("roomId")),
                            pDAO.searchPetById(rs.getString("petId")),rs.getDate("checkInDate"),rs.getDate("checkOutDate"));
                    if (b != null) {
                        result = b;
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

}
