package com.practrack.controller;

import com.practrack.dao.NotificationDAO;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NotificationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();

            long userId =
                    Long.parseLong(
                            session.getAttribute("userId").toString()
                    );

            String role =
                    session.getAttribute("role").toString();

            String userType;

            if ("student".equalsIgnoreCase(role)) {
                userType = "STUDENT";
            } else {
                userType = "TEACHER";
            }

            NotificationDAO dao = new NotificationDAO();

            ResultSet rs =
                    dao.getNotifications(userType, userId);

            request.setAttribute("notifications", rs);

            if ("STUDENT".equals(userType)) {

                request.getRequestDispatcher(
                        "/WEB-INF/Student/notifications.jsp"
                ).forward(request, response);

            } else {

                request.getRequestDispatcher(
                        "/WEB-INF/Teacher/notifications.jsp"
                ).forward(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error loading notifications</h2>"
            );

            response.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
            );
        }
    }
}