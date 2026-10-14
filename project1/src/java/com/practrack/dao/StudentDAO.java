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


    // Add a new student
   public int addStudent(String name,
                      String rollNo,
                      String email,
                      int semester,
                      String section)
        throws SQLException {

    String sql =
            "INSERT INTO students " +
            "(student_id, name, roll_no, email, password, semester, section) " +
            "VALUES (student_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";

    Connection con = dbconnection.getConnection();

    PreparedStatement ps =
            con.prepareStatement(sql);

    ps.setString(1, name);
    ps.setString(2, rollNo);
    ps.setString(3, email);

    // Temporary password
    ps.setString(4, "student123");

    ps.setInt(5, semester);
    ps.setString(6, section);

    int result =
            ps.executeUpdate();

    ps.close();
    con.close();

    return result;
}
   public ResultSet getAllStudents()
        throws SQLException {

    String sql =
            "SELECT student_id, name, roll_no, email, " +
            "semester, section " +
            "FROM students " +
            "ORDER BY student_id";

    Connection con =
            dbconnection.getConnection();

    PreparedStatement ps =
            con.prepareStatement(sql);

    return ps.executeQuery();
}
   public ResultSet getStudentById(long studentId)
        throws SQLException {

    String sql =
            "SELECT student_id, name, roll_no, email, " +
            "semester, section " +
            "FROM students " +
            "WHERE student_id = ?";

    Connection con =
            dbconnection.getConnection();

    PreparedStatement ps =
            con.prepareStatement(sql);

    ps.setLong(1, studentId);

    return ps.executeQuery();
}


public int updateStudent(long studentId,
                         String name,
                         String rollNo,
                         String email,
                         int semester,
                         String section)
        throws SQLException {

    String sql =
            "UPDATE students " +
            "SET name = ?, " +
            "roll_no = ?, " +
            "email = ?, " +
            "semester = ?, " +
            "section = ? " +
            "WHERE student_id = ?";

    Connection con =
            dbconnection.getConnection();

    PreparedStatement ps =
            con.prepareStatement(sql);

    ps.setString(1, name);
    ps.setString(2, rollNo);
    ps.setString(3, email);
    ps.setInt(4, semester);
    ps.setString(5, section);
    ps.setLong(6, studentId);

    int result =
            ps.executeUpdate();

    ps.close();
    con.close();

    return result;
}
public int deleteStudent(long studentId)
        throws SQLException {

    String sql =
            "DELETE FROM students " +
            "WHERE student_id = ?";

    Connection con =
            dbconnection.getConnection();

    PreparedStatement ps =
            con.prepareStatement(sql);

    ps.setLong(1, studentId);

    int result =
            ps.executeUpdate();

    ps.close();
    con.close();

    return result;
}
}