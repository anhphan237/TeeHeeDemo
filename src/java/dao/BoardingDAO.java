/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.BoardingDTO;
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
            String sql = "select a.*, c.latestPrice from [dbo].[boarding] a \n"
                    + "join\n"
                    + "(SELECT\n"
                    + "    boardingId,\n"
                    + "    (SELECT TOP 1 date FROM [dbo].[Boarding_Price] bp\n"
                    + "     WHERE bp.boardingId = b.boardingId\n"
                    + "     ORDER BY date DESC) AS latestDate,\n"
                    + "    (SELECT TOP 1 price FROM [dbo].[Boarding_Price] bp\n"
                    + "     WHERE bp.boardingId = b.boardingId\n"
                    + "     ORDER BY date DESC) AS latestPrice\n"
                    + "FROM\n"
                    + "    [dbo].[Boarding_Price] b\n"
                    + "GROUP BY\n"
                    + "    boardingId) c on a.boardingId = c.boardingID";

            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {

                BoardingDTO c;
                c = new BoardingDTO(rs.getString("boardingId"), rs.getString("name"),
                        rs.getDouble("rate"), rs.getString("description").split("#"), rs.getString("img"),
                        rs.getDouble("length"), rs.getDouble("height"), rs.getDouble("width"), rs.getDouble("maxWeight"),
                        rs.getBoolean("status"), rs.getDouble("latestPrice"));
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

    public BoardingDTO insertBoarding(String name, String description_raw, String img,
            double length, double height, double width, double maxWeight, double price) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean status = true;
        BoardingDTO b;
        String boardingId = this.createBoardingId();
        String[] description = description_raw.split("#");

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "exec insert_boarding ?,?,?,?,?,?,?,?,?,?";

                stm = con.prepareStatement(sql);

                stm.setString(1, boardingId);
                stm.setString(2, name);
                stm.setString(3, description_raw);
                stm.setString(4, img);
                stm.setDouble(5, length);
                stm.setDouble(6, height);
                stm.setDouble(7, width);
                stm.setDouble(8, maxWeight);
                stm.setBoolean(9, status);
                stm.setDouble(10, price);

                b = new BoardingDTO(boardingId, name, 0, description, img, length, height, width, maxWeight, status, price);

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
                    newBoardingId = String.format("B%03d", count + 1);
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
                String sql = "EXEC update_Boarding\n"
                        + "    @boardingId = ?,\n"
                        + "    @name = ?,\n"
                        + "    @rate = ?,\n"
                        + "    @description = ?,\n"
                        + "    @img = ?,\n"
                        + "    @length = ?,\n"
                        + "    @height = ?,\n"
                        + "    @width = ?,\n"
                        + "    @maxWeight = ?,\n"
                        + "    @status = ?,\n"
                        + "    @price = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, s.getBoardingId());
                stm.setString(2, s.getName());
                stm.setDouble(3, s.getRate());
                stm.setString(4, convertArrayToString(s.getDescription()));
                stm.setString(5, s.getImg());
                stm.setDouble(6, s.getLength());
                stm.setDouble(7, s.getHeight());
                stm.setDouble(8, s.getWidth());
                stm.setDouble(9, s.getMaxWeight());
                stm.setBoolean(10, s.isStatus());
                stm.setDouble(11, s.getPrice());

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

    public String convertArrayToString(String[] x) {
        String rs = null;

        for (String string : x) {
            rs += string + " ";
        }
        return rs;
    }

    public BoardingDTO searchBoardingById(String boardingId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT a.[boardingId]\n"
                        + "      ,[name]\n"
                        + "      ,[rate]\n"
                        + "      ,[description]\n"
                        + "      ,[img]\n"
                        + "      ,[length]\n"
                        + "      ,[height]\n"
                        + "      ,[width]\n"
                        + "      ,[maxWeight]\n"
                        + "      ,[status]\n"
                        + "	  ,b.price\n"
                        + "  FROM [dbo].[boarding] a,[dbo].[Boarding_Price] b\n"
                        + "  where a.boardingId = b.boardingID and a.boardingId = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, boardingId);
                rs = stm.executeQuery();

                if (rs.next()) {
                    BoardingDTO b = new BoardingDTO(boardingId, rs.getString("name"), rs.getDouble("rate"),
                            rs.getString("description").split("#"), rs.getString("img"), rs.getDouble("length"),
                            rs.getDouble("height"), rs.getDouble("width"),
                            rs.getDouble("maxWeight"), rs.getBoolean("status"), rs.getDouble("price"));
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
                String sql = "SELECT top 1 a.[boardingId]\n"
                        + "      ,[name]\n"
                        + "      ,[rate]\n"
                        + "      ,[description]\n"
                        + "      ,[img]\n"
                        + "      ,[length]\n"
                        + "      ,[height]\n"
                        + "      ,[width]\n"
                        + "      ,[maxWeight]\n"
                        + "      ,[status],\n"
                        + "	  b.price\n"
                        + "  FROM [dbo].[boarding] a, [dbo].[Boarding_Price] b\n"
                        + "  where name like ? and a.boardingId = b.boardingID "
                        + "and a.boardingId = ?\n"
                        + "  order by date desc";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    BoardingDTO b = new BoardingDTO(rs.getString("boardingId"), rs.getString("name"), rs.getDouble("rate"),
                            rs.getString("description").split(","), rs.getString("img"), rs.getDouble("length"), rs.getDouble("height"), rs.getDouble("width"),
                            rs.getDouble("maxWeight"), rs.getBoolean("status"), rs.getDouble("price"));
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
