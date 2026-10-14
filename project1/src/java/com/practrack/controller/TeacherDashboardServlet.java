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

@WebServlet("/teacherDashboard")
public class TeacherDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            HttpSession session = request.getSession();

            Object userIdObject =
                    session.getAttribute("userId");

            if (userIdObject == null) {
                response.sendRedirect(
                        request.getContextPath()
                        + "/login.jsp"
                );
                return;
            }

            long teacherId =
                    Long.parseLong(
                            userIdObject.toString()
                    );

            con = dbconnection.getConnection();

            // Total Students
            ps = con.prepareStatement(
                    "SELECT COUNT(*) FROM students"
            );
            rs = ps.executeQuery();
            rs.next();

            int totalStudents = rs.getInt(1);

            rs.close();
            ps.close();

            // Total Submissions
            ps = con.prepareStatement(
                    "SELECT COUNT(*) FROM submissions"
            );
            rs = ps.executeQuery();
            rs.next();

            int submitted = rs.getInt(1);

            rs.close();
            ps.close();

            // Checked Submissions
            ps = con.prepareStatement(
                    "SELECT COUNT(*) FROM submissions " +
                    "WHERE status = 'CHECKED'"
            );
            rs = ps.executeQuery();
            rs.next();

            int checked = rs.getInt(1);

            rs.close();
            ps.close();

            // Pending = SUBMITTED + RESUBMIT
            ps = con.prepareStatement(
                    "SELECT COUNT(*) FROM submissions " +
                    "WHERE status IN ('SUBMITTED', 'RESUBMIT')"
            );
            rs = ps.executeQuery();
            rs.next();

            int pending = rs.getInt(1);

            rs.close();
            ps.close();

            // Send dashboard values
            request.setAttribute(
                    "totalStudents",
                    totalStudents
            );

            request.setAttribute(
                    "submitted",
                    submitted
            );

            request.setAttribute(
                    "checked",
                    checked
            );

            request.setAttribute(
                    "pending",
                    pending
            );

            // Latest Teacher Notification
            NotificationDAO notificationDAO =
                    new NotificationDAO();

            String latestNotification =
                    notificationDAO.getLatestNotificationMessage(
                            "TEACHER",
                            teacherId
                    );

            request.setAttribute(
                    "latestNotification",
                    latestNotification
            );

            request.getRequestDispatcher(
                    "/WEB-INF/Teacher/dashboard.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error loading teacher dashboard</h2>"
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