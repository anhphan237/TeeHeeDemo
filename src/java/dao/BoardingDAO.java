/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.BoardingDTO;
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
public class BoardingDAO {

    public ArrayList<BoardingDTO> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<BoardingDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [boardingId]\n"
                    + "      ,[name]\n"
                    + "      ,[rate]\n"
                    + "      ,[description]\n"
                    + "      ,[img]\n"
                    + "      ,[length]\n"
                    + "      ,[height]\n"
                    + "      ,[width]\n"
                    + "      ,[maxWeight]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[boarding]";

            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                BoardingDTO c = new BoardingDTO(rs.getString("boardingId"), rs.getString("name"),
                        rs.getDouble("rate"), rs.getString("description"), rs.getString("img"),
                        rs.getDouble("length"), rs.getDouble("height"), rs.getDouble("width"), rs.getDouble("maxWeight"),
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

    public BoardingDTO insertBoarding(String name, double rate, String description, String img,
            double length, double height, double width, double maxWeight) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean status = true;
        BoardingDTO b = null;
        String boardingId = this.createBoardingId();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[boarding]\n"
                        + "           ([boardingId]\n"
                        + "           ,[name]\n"
                        + "           ,[rate]\n"
                        + "           ,[description]\n"
                        + "           ,[img]\n"
                        + "           ,[length]\n"
                        + "           ,[height]\n"
                        + "           ,[width]\n"
                        + "           ,[maxWeight]\n"
                        + "           ,[status])"
                        + "    VALUES (?,?,?,?,?,?,?,?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, boardingId);
                stm.setString(2, name);
                stm.setDouble(3, rate);
                stm.setString(4, description);
                stm.setString(5, img);
                stm.setDouble(6, length);
                stm.setDouble(7, height);
                stm.setDouble(8, width);
                stm.setDouble(9, maxWeight);
                stm.setBoolean(10, status);

                b = new BoardingDTO(boardingId, name, rate, description, img, length, height, width, maxWeight, status);

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
        return null;
    }

    private String createBoardingId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newBoardingId = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from Boarding";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newBoardingId = String.format("B%04d", count + 1);
                }
            }
            if (newBoardingId != null) {
                return newBoardingId;
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

    public boolean updateBoarding(BoardingDTO s)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[boarding]\n"
                        + "   SET [name] = ?\n"
                        + "      ,[rate] = ?\n"
                        + "      ,[description] = ?\n"
                        + "      ,[img] = ?\n"
                        + "      ,[length] = ?\n"
                        + "      ,[height] = ?\n"
                        + "      ,[width] = ?\n"
                        + "      ,[maxWeight] = ?\n"
                        + "      ,[status] = ?\n"
                        + " WHERE [boardingId] = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, s.getName());
                stm.setDouble(2, s.getRate());
                stm.setString(3, s.getDescription());
                stm.setString(4, s.getImg());
                stm.setDouble(5, s.getLength());
                stm.setDouble(6, s.getHeight());
                stm.setDouble(7, s.getWidth());
                stm.setDouble(8, s.getMaxWeight());
                stm.setBoolean(9, s.isStatus());
                stm.setString(10, s.getBoardingId());

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

    public BoardingDTO searchBoardingById(String boardingId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [boardingId]\n"
                        + "      ,[name]\n"
                        + "      ,[rate]\n"
                        + "      ,[description]\n"
                        + "      ,[img]\n"
                        + "      ,[length]\n"
                        + "      ,[height]\n"
                        + "      ,[width]\n"
                        + "      ,[maxWeight]\n"
                        + "      ,[status]\n"
                        + "  FROM [dbo].[boarding]"
                        + "  where boardingId = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, boardingId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    BoardingDTO b = new BoardingDTO(boardingId, rs.getString("name"), rs.getDouble("rate"),
                            rs.getString("description"), rs.getString("img"),rs.getDouble("length"),rs.getDouble("height"),rs.getDouble("width"),
                            rs.getDouble("maxWeight"), rs.getBoolean("status"));
                    if (b != null) {
                        return b;
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
        return null;
    }
    
    public void deleteBoarding(String boardingId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean status = false;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[boarding]\n"
                        + "   SET [status] = ?\n"
                        + " WHERE [boardingId] = ?";

                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setString(2, boardingId);
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
    }
    
    public ArrayList<BoardingDTO> searchBoardingByName(String name) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        ArrayList<BoardingDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [boardingId]\n"
                        + "      ,[name]\n"
                        + "      ,[rate]\n"
                        + "      ,[description]\n"
                        + "      ,[img]\n"
                        + "      ,[length]\n"
                        + "      ,[height]\n"
                        + "      ,[width]\n"
                        + "      ,[maxWeight]\n"
                        + "      ,[status]\n"
                        + "  FROM [dbo].[boarding]"
                        + "  where name like ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    BoardingDTO b = new BoardingDTO(rs.getString("boardingId"), rs.getString("name"), rs.getDouble("rate"),
                            rs.getString("description"), rs.getString("img"),rs.getDouble("length"),rs.getDouble("height"),rs.getDouble("width"),
                            rs.getDouble("maxWeight"), rs.getBoolean("status"));
                    list.add(b);
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
        return list;
    }
}
