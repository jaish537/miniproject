package com.practrack.controller;

import com.practrack.dao.NotificationDAO;
import com.practrack.dao.PracticalDAO;
import com.practrack.dao.StudentDAO;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addPractical")
public class AddPracticalServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String subjectId =
                request.getParameter("subjectId");

        String practicalNo =
                request.getParameter("practicalNo");

        String title =
                request.getParameter("title");

        String description =
                request.getParameter("description");

        String deadline =
                request.getParameter("deadline");

        try {

            PracticalDAO dao =
                    new PracticalDAO();

            int result =
                    dao.addPractical(
                            Long.parseLong(subjectId),
                            Integer.parseInt(practicalNo),
                            title,
                            description,
                            Date.valueOf(deadline)
                    );

            if (result > 0) {

                /*
                 * Notify all students about the new practical
                 */

                StudentDAO studentDAO =
                        new StudentDAO();

                ResultSet students =
                        studentDAO.getAllStudents();

                NotificationDAO notificationDAO =
                        new NotificationDAO();

                String message =
                        "New practical added: "
                        + title
                        + ". Deadline: "
                        + deadline
                        + ". Please check your practicals.";

                while (students.next()) {

                    long studentId =
                            students.getLong("student_id");

                    notificationDAO.addNotification(
                            "STUDENT",
                            studentId,
                            message
                    );
                }

                students.close();

                response.sendRedirect(
                        request.getContextPath()
                        + "/teacherDashboard"
                );

            } else {

                response.getWriter().println(
                        "<h2>Practical could not be added</h2>"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error adding practical</h2>"
            );

            response.getWriter().println(
                    "<p>"
                    + e.getMessage()
                    + "</p>"
            );
        }
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/Teacher/addPractical.jsp"
        ).forward(request, response);
    }
}