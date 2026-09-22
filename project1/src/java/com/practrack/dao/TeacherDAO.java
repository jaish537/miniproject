package com.practrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO {

    public boolean validateLogin(String email, String password)
            throws SQLException {

        String sql = "SELECT COUNT(*) FROM teachers " +
                     "WHERE email = ? AND password = ?";

        Connection con = DBConnection.getConnection();
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

    public ResultSet getTeacherByEmail(String email)
            throws SQLException {

        String sql = "SELECT * FROM teachers WHERE email = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, email);

        return ps.executeQuery();
    }

    public ResultSet getAllSubmissions()
            throws SQLException {

        String sql =
                "SELECT s.submission_id, " +
                "st.name AS student_name, " +
                "st.roll_no, " +
                "p.title, " +
                "s.file_name, " +
                "s.submitted_at, " +
                "s.status " +
                "FROM submissions s " +
                "JOIN students st ON s.student_id = st.student_id " +
                "JOIN practicals p ON s.practical_id = p.practical_id " +
                "ORDER BY s.submitted_at DESC";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();
    }

    public int saveReview(long submissionId,
                          long teacherId,
                          String status,
                          String remarks)
            throws SQLException {

        String sql =
                "INSERT INTO reviews " +
                "(review_id, submission_id, teacher_id, status, remarks) " +
                "VALUES (review_seq.NEXTVAL, ?, ?, ?, ?)";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, submissionId);
        ps.setLong(2, teacherId);
        ps.setString(3, status);
        ps.setString(4, remarks);

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }

    public int updateSubmissionStatus(long submissionId,
                                      String status)
            throws SQLException {

        String sql =
                "UPDATE submissions SET status = ? " +
                "WHERE submission_id = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, status);
        ps.setLong(2, submissionId);

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }
}
