package com.practrack.controller;

import com.practrack.dao.NotificationDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/clearNotifications")
public class ClearNotificationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session =
                    request.getSession();

            Object userIdObject =
                    session.getAttribute("userId");

            Object roleObject =
                    session.getAttribute("role");

            if (userIdObject == null ||
                roleObject == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/login.jsp"
                );

                return;
            }

            long userId =
                    Long.parseLong(
                            userIdObject.toString()
                    );

            String role =
                    roleObject.toString();

            String userType;

            if ("student".equalsIgnoreCase(role)) {
                userType = "STUDENT";
            } else {
                userType = "TEACHER";
            }

            NotificationDAO dao =
                    new NotificationDAO();

            dao.clearNotifications(
                    userType,
                    userId
            );

            response.sendRedirect(
                    request.getContextPath()
                    + "/notifications"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error clearing notifications</h2>"
            );

            response.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
            );
        }
    }
}