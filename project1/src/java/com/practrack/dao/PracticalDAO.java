package com.practrack.dao;

import com.practrack.util.dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PracticalDAO {

    public ResultSet getAllPracticals()
            throws SQLException {

        String sql =
                "SELECT * FROM practicals " +
                "ORDER BY practical_no";

        Connection con = dbconnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();
    }

    public ResultSet getPracticalById(long practicalId)
            throws SQLException {

        String sql =
                "SELECT * FROM practicals " +
                "WHERE practical_id = ?";

        Connection con = dbconnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, practicalId);

        return ps.executeQuery();
    }

    public int addPractical(long subjectId,
                            int practicalNo,
                            String title,
                            String description,
                            java.sql.Date deadline)
            throws SQLException {

        String sql =
                "INSERT INTO practicals " +
                "(practical_id, subject_id, practical_no, title, description, deadline) " +
                "VALUES (practical_seq.NEXTVAL, ?, ?, ?, ?, ?)";

        Connection con = dbconnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, subjectId);
        ps.setInt(2, practicalNo);
        ps.setString(3, title);
        ps.setString(4, description);
        ps.setDate(5, deadline);

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }
    public java.sql.Date getDeadlineByPracticalId(long practicalId)
        throws SQLException {

    String sql =
            "SELECT deadline FROM practicals " +
            "WHERE practical_id = ?";

    Connection con = dbconnection.getConnection();
    PreparedStatement ps = con.prepareStatement(sql);

    ps.setLong(1, practicalId);

    ResultSet rs = ps.executeQuery();

    java.sql.Date deadline = null;

    if (rs.next()) {
        deadline = rs.getDate("deadline");
    }

    rs.close();
    ps.close();
    con.close();

    return deadline;
}
    public int updatePractical(long practicalId,
                           int practicalNo,
                           String title,
                           String description,
                           java.sql.Date deadline)
        throws SQLException {

    String sql =
            "UPDATE practicals " +
            "SET practical_no = ?, " +
            "title = ?, " +
            "description = ?, " +
            "deadline = ? " +
            "WHERE practical_id = ?";

    Connection con = dbconnection.getConnection();
    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, practicalNo);
    ps.setString(2, title);
    ps.setString(3, description);
    ps.setDate(4, deadline);
    ps.setLong(5, practicalId);

    int result = ps.executeUpdate();

    ps.close();
    con.close();

    return result;
}
   public int deletePractical(long practicalId)
        throws SQLException {

    Connection con = null;
    PreparedStatement ps = null;

    try {

        con = dbconnection.getConnection();

        con.setAutoCommit(false);

        // Delete reviews linked to submissions
        ps = con.prepareStatement(
                "DELETE FROM reviews " +
                "WHERE submission_id IN " +
                "(SELECT submission_id " +
                "FROM submissions " +
                "WHERE practical_id = ?)"
        );

        ps.setLong(1, practicalId);
        ps.executeUpdate();

        ps.close();

        // Delete submissions linked to practical
        ps = con.prepareStatement(
                "DELETE FROM submissions " +
                "WHERE practical_id = ?"
        );

        ps.setLong(1, practicalId);
        ps.executeUpdate();

        ps.close();

        // Delete practical
        ps = con.prepareStatement(
                "DELETE FROM practicals " +
                "WHERE practical_id = ?"
        );

        ps.setLong(1, practicalId);

        int result =
                ps.executeUpdate();

        con.commit();

        return result;

    } catch (SQLException e) {

        if (con != null) {
            con.rollback();
        }

        throw e;

    } finally {

        if (ps != null) {
            ps.close();
        }

        if (con != null) {
            con.setAutoCommit(true);
            con.close();
        }
    }
}
}