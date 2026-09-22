package com.practrack.dao;

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

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();
    }

    public ResultSet getPracticalById(long practicalId)
            throws SQLException {

        String sql =
                "SELECT * FROM practicals " +
                "WHERE practical_id = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, practicalId);

        return ps.executeQuery();
    }
}