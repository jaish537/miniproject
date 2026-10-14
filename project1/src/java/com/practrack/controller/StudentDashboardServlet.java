package com.practrack.controller;

import com.practrack.dao.NotificationDAO;
import com.practrack.util.dbconnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/studentDashboard")
public class StudentDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Object userIdObject =
                session.getAttribute("userId");

        if (userIdObject == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );
            return;
        }

        long studentId =
                Long.parseLong(userIdObject.toString());

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql =
                    "SELECT p.practical_id, " +
                    "p.practical_no, " +
                    "p.title, " +
                    "p.deadline, " +
                    "(SELECT s.status " +
                    " FROM submissions s " +
                    " WHERE s.student_id = ? " +
                    " AND s.practical_id = p.practical_id " +
                    " AND s.submission_id = " +
                    "     (SELECT MAX(s2.submission_id) " +
                    "      FROM submissions s2 " +
                    "      WHERE s2.student_id = ? " +
                    "      AND s2.practical_id = p.practical_id)" +
                    ") AS status " +
                    "FROM practicals p " +
                    "ORDER BY p.practical_no";

            con = dbconnection.getConnection();

            ps = con.prepareStatement(sql);

            ps.setLong(1, studentId);
            ps.setLong(2, studentId);

            rs = ps.executeQuery();

            request.setAttribute(
                    "practicals",
                    rs
            );

            // Latest notification
            NotificationDAO notificationDAO =
                    new NotificationDAO();

            String latestNotification =
                    notificationDAO.getLatestNotificationMessage(
                            "STUDENT",
                            studentId
                    );

            request.setAttribute(
                    "latestNotification",
                    latestNotification
            );

            request.getRequestDispatcher(
                    "/WEB-INF/Student/studentdashboard.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error loading dashboard</h2>"
            );

            response.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
            );

        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}