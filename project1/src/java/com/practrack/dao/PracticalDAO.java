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
}