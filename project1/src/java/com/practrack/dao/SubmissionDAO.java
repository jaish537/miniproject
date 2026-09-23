package com.practrack.dao;

import com.practrack.util.dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubmissionDAO {

    public int submitPractical(long studentId,
                               long practicalId,
                               String fileName)
            throws SQLException {

        String sql =
                "INSERT INTO submissions " +
                "(submission_id, student_id, practical_id, file_name, status) " +
                "VALUES (submission_seq.NEXTVAL, ?, ?, ?, 'SUBMITTED')";

        Connection con = dbconnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, studentId);
        ps.setLong(2, practicalId);
        ps.setString(3, fileName);

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }

    public ResultSet getSubmissionsByStudent(long studentId)
            throws SQLException {

        String sql =
                "SELECT s.submission_id, " +
                "p.title, " +
                "s.file_name, " +
                "s.submitted_at, " +
                "s.status " +
                "FROM submissions s " +
                "JOIN practicals p " +
                "ON s.practical_id = p.practical_id " +
                "WHERE s.student_id = ? " +
                "ORDER BY s.submitted_at DESC";

        Connection con = dbconnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, studentId);

        return ps.executeQuery();
    }

    public ResultSet getSubmissionById(long submissionId)
            throws SQLException {

        String sql =
                "SELECT s.submission_id, " +
                "s.student_id, " +
                "s.practical_id, " +
                "s.file_name, " +
                "s.submitted_at, " +
                "s.status, " +
                "st.name AS student_name, " +
                "p.title AS practical_title " +
                "FROM submissions s " +
                "JOIN students st " +
                "ON s.student_id = st.student_id " +
                "JOIN practicals p " +
                "ON s.practical_id = p.practical_id " +
                "WHERE s.submission_id = ?";

        Connection con = dbconnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, submissionId);

        return ps.executeQuery();
    }

    public int updateStatus(long submissionId,
                            String status)
            throws SQLException {

        String sql =
                "UPDATE submissions " +
                "SET status = ? " +
                "WHERE submission_id = ?";

        Connection con = dbconnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, status);
        ps.setLong(2, submissionId);

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }

    public long getStudentIdBySubmission(long submissionId)
            throws SQLException {

        String sql =
                "SELECT student_id FROM submissions " +
                "WHERE submission_id = ?";

        Connection con = dbconnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, submissionId);

        ResultSet rs = ps.executeQuery();

        long studentId = 0;

        if (rs.next()) {
            studentId = rs.getLong("student_id");
        }

        rs.close();
        ps.close();
        con.close();

        return studentId;
    }
}