package com.practrack.dao;

import com.practrack.util.dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public boolean validateLogin(String email, String password)
            throws SQLException {

        String sql = "SELECT COUNT(*) FROM students " +
                     "WHERE email = ? AND password = ?";

        Connection con = dbconnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        boolean result = false;

        if (rs.next()) {
            result = rs.getInt(1) > 0;
        }

        rs.close();
        ps.close();
        con.close();

        return result;
    }

    public ResultSet getStudentByEmail(String email)
            throws SQLException {

        String sql = "SELECT * FROM students WHERE email = ?";

        Connection con = dbconnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, email);

        return ps.executeQuery();
    }
}