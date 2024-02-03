/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.RoomDTO;
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
public class RoomDAO {

    public ArrayList<RoomDTO> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<RoomDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        BoardingDAO bDAO = new BoardingDAO();

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [roomID]\n"
                    + "      ,[boardingID]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Room]";

            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                RoomDTO r = new RoomDTO(rs.getString("roomId"), bDAO.searchBoardingById(rs.getString("boardingId")), rs.getBoolean("status"));
                list.add(r);
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

    public boolean insertRoom(String boardingId, boolean status) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        String roomId = createRoomId();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Room]\n"
                        + "           ([roomID]\n"
                        + "           ,[boardingID]\n"
                        + "           ,[status])"
                        + "     VALUES (?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, roomId);
                stm.setString(2, boardingId);
                stm.setBoolean(3, status);

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

    public String createRoomId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newRoomId = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from Room";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newRoomId = String.format("R%03d", count + 1);
                }
            }
            if (newRoomId != null) {
                return newRoomId;
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
}
