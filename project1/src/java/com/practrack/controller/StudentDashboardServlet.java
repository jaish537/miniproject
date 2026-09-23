package com.practrack.controller;

import com.practrack.util.dbconnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StudentDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            HttpSession session = request.getSession();

            long studentId = Long.parseLong(
                    session.getAttribute("userId").toString()
            );

            con = dbconnection.getConnection();

            String sql =
        "SELECT p.practical_no, " +
        "p.title, " +
        "s.status, " +
        "s.submission_id " +
        "FROM practicals p " +
        "LEFT JOIN ( " +
        "    SELECT s1.* " +
        "    FROM submissions s1 " +
        "    WHERE s1.student_id = ? " +
        "    AND s1.submission_id = ( " +
        "        SELECT MAX(s2.submission_id) " +
        "        FROM submissions s2 " +
        "        WHERE s2.student_id = s1.student_id " +
        "        AND s2.practical_id = s1.practical_id " +
        "    ) " +
        ") s " +
        "ON p.practical_id = s.practical_id " +
        "ORDER BY p.practical_no";

            ps = con.prepareStatement(sql);
            ps.setLong(1, studentId);
           

            rs = ps.executeQuery();

            request.setAttribute("practicals", rs);

            request.getRequestDispatcher(
                    "/WEB-INF/Student/studentdashboard.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(
                    "<h2>Error loading student dashboard</h2>"
            );

            response.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
            );

        } finally {

            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}