package dao;

import dto.PetDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBHelper;

public class PetDAO {

    public ArrayList<PetDTO> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<PetDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [petID]\n"
                    + "      ,[name]\n"
                    + "      ,[dob]\n"
                    + "      ,[customerID]\n"
                    + "      ,[type]\n"
                    + "      ,[weight]\n"
                    + "      ,[gender]\n"
                    + "      ,[vaccinated]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Pet]";

            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                PetDTO p = new PetDTO(rs.getString("petId"), rs.getString("name"),
                        rs.getDate("dob"), rs.getString("customerId"), rs.getBoolean("type"),
                        rs.getDouble("weight"), rs.getBoolean("gender"), rs.getBoolean("vaccinated"), rs.getBoolean("status"));
                list.add(p);
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

    public boolean insertPet(String name, Date dob, String customerId, boolean type,
            double weight, boolean gender, boolean vaccinated, boolean status) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        String petId = createPetId();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Pet]\n"
                        + "           ([petID]\n"
                        + "           ,[name]\n"
                        + "           ,[dob]\n"
                        + "           ,[customerID]\n"
                        + "           ,[type]\n"
                        + "           ,[weight]\n"
                        + "           ,[gender]\n"
                        + "           ,[vaccinated]\n"
                        + "           ,[status])"
                        + "     VALUES (?,?,?,?,?,?,?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, petId);
                stm.setString(2, name);
                stm.setDate(3, dob);
                stm.setString(4, customerId);
                stm.setBoolean(5, type);
                stm.setDouble(6, weight);
                stm.setBoolean(7, gender);
                stm.setBoolean(8, vaccinated);
                stm.setBoolean(9, status);

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

    public String createPetId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newPetId = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from Pet";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newPetId = String.format("P%04d", count + 1);
                }
            }
            if (newPetId != null) {
                return newPetId;
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

    public PetDTO searchPetById(String petId) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [petID]\n"
                    + "      ,[name]\n"
                    + "      ,[dob]\n"
                    + "      ,[customerID]\n"
                    + "      ,[type]\n"
                    + "      ,[weight]\n"
                    + "      ,[gender]\n"
                    + "      ,[vaccinated]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Pet]"
                    + "  WHERE [petID] = ?";
            st = con.prepareStatement(sql);
            st.setString(1, petId);
            rs = st.executeQuery();
            if (rs.next()) {
                PetDTO c = new PetDTO(rs.getString("petId"), rs.getString("name"),
                        rs.getDate("dob"), rs.getString("customerId"), rs.getBoolean("type"),
                        rs.getDouble("weight"), rs.getBoolean("gender"), rs.getBoolean("vaccinated"),
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

    public boolean updatePet(PetDTO p)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Pet]\n"
                        + "   SET [petID] = ?\n"
                        + "      ,[name] = ?\n"
                        + "      ,[dob] = ?\n"
                        + "      ,[customerID] = ?\n"
                        + "      ,[type] = ?\n"
                        + "      ,[weight] = ?\n"
                        + "      ,[gender] = ?\n"
                        + "      ,[vaccinated] = ?\n"
                        + "      ,[status] = ?"
                        + " WHERE [PetID] = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, p.getPetId());
                stm.setString(2, p.getName());
                stm.setDate(3, p.getDob());
                stm.setString(4, p.getCustomerId());
                stm.setBoolean(5, p.isType());
                stm.setDouble(6, p.getWeight());
                stm.setBoolean(7, p.isGender());
                stm.setBoolean(8, p.isVaccinated());
                stm.setBoolean(9, p.isStatus());
                stm.setString(10, p.getPetId());

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
    
    public boolean deletePet(String petId)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;
        boolean status = false;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Pet]\n"
                        + "   SET [status] = ?\n"
                        + " WHERE [petID] = ?";

                stm = con.prepareStatement(sql);

                stm.setBoolean(1, status);
                stm.setString(2, petId);

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
    
    public ArrayList<PetDTO> getPetByCusId(String customerId) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<PetDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [petID]\n"
                    + "      ,[name]\n"
                    + "      ,[dob]\n"
                    + "      ,[customerID]\n"
                    + "      ,[type]\n"
                    + "      ,[weight]\n"
                    + "      ,[gender]\n"
                    + "      ,[vaccinated]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Pet]"
                    + "  WHERE [customerID] = ?";
            st = con.prepareStatement(sql);
            st.setString(1, customerId);
            rs = st.executeQuery();
            while (rs.next()) {
                PetDTO c = new PetDTO(rs.getString("petId"), rs.getString("name"),
                        rs.getDate("dob"), rs.getString("customerId"), rs.getBoolean("type"),
                        rs.getDouble("weight"), rs.getBoolean("gender"), rs.getBoolean("vaccinated"),
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
}
