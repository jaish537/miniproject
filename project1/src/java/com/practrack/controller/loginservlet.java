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


public class loginservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
            
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dbconnection.getConnection();

            // Check Student
            String studentSql =
                    "SELECT student_id, name FROM students "
                    + "WHERE email = ? AND password = ?";

            ps = con.prepareStatement(studentSql);
            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {

                HttpSession session = request.getSession();
                session.setAttribute("userId", rs.getInt("student_id"));
                session.setAttribute("userName", rs.getString("name"));
                session.setAttribute("userEmail", email);
                session.setAttribute("role", "student");

                response.sendRedirect(
                request.getContextPath() + "/studentDashboard");

                return;
            }

            rs.close();
            ps.close();

            // Check Teacher
            String teacherSql =
                    "SELECT teacher_id, name FROM teachers "
                    + "WHERE email = ? AND password = ?";

            ps = con.prepareStatement(teacherSql);
            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {

                HttpSession session = request.getSession();
                session.setAttribute("userId", rs.getInt("teacher_id"));
                session.setAttribute("userName", rs.getString("name"));
                session.setAttribute("userEmail", email);
                session.setAttribute("role", "teacher");

                response.sendRedirect(
                request.getContextPath() + "/teacherDashboard");
                return;
            }

           // Login failed
            request.getRequestDispatcher("/invalidLogin.jsp")
            .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h2>Database Error</h2>");
            response.getWriter().println("<p>" +e.getMessage()+ "</p>");

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

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(
                request.getContextPath() + "/login.jsp"
        );
    }
}