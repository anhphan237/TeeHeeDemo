/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.ServicePriceDTO;
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
public class ServicePriceDAO {

    public ArrayList<ServicePriceDTO> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<ServicePriceDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT sp.[serviceID], \n"
                    + "       MAX(sp.[date]) AS max_date, \n"
                    + "       sp.type, \n"
                    + "       sp.weight, \n"
                    + "       (SELECT TOP 1 sp2.price \n"
                    + "        FROM [dbo].[Service_Price] sp2 \n"
                    + "        WHERE sp2.serviceID = sp.serviceID \n"
                    + "          AND sp2.type = sp.type \n"
                    + "          AND sp2.weight = sp.weight \n"
                    + "        ORDER BY sp2.date DESC) AS price\n"
                    + "FROM [dbo].[Service_Price] sp\n"
                    + "GROUP BY sp.[serviceID], sp.type, sp.weight";

            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                ServicePriceDTO o = new ServicePriceDTO(rs.getString("serviceID"),
                rs.getDate("max_date"),rs.getBoolean("type"), rs.getDouble("weight"),
                rs.getDouble("price"));
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

    public ServicePriceDTO getServicePriceById(String serviceId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT top 1 [serviceID]\n"
                        + "      ,[date]\n"
                        + "      ,[type]\n"
                        + "      ,[weight]\n"
                        + "      ,[price]\n"
                        + "  FROM [dbo].[Service_Price]\n"
                        + "  where [serviceID] = ?\n"
                        + "  order by date desc";

                stm = con.prepareStatement(sql);
                stm.setString(1, serviceId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    ServicePriceDTO b = new ServicePriceDTO(serviceId, rs.getDate("date"), rs.getBoolean("type"),
                            rs.getDouble("weight"), rs.getDouble("price"));
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

}
