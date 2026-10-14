package com.practrack.controller;

import com.practrack.dao.StudentDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String studentIdText =
                    request.getParameter("studentId");

            if (studentIdText == null ||
                studentIdText.trim().isEmpty()) {

                throw new Exception(
                        "Student ID is required."
                );
            }

            long studentId =
                    Long.parseLong(studentIdText);

            StudentDAO dao =
                    new StudentDAO();

            int result =
                    dao.deleteStudent(studentId);

            if (result > 0) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/manageStudents"
                );

            } else {

                throw new Exception(
                        "Student could not be deleted."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error deleting student</h2>"
            );

            response.getWriter().println(
                    "<p>"
                    + e.getMessage()
                    + "</p>"
            );

            response.getWriter().println(
                    "<p><a href='"
                    + request.getContextPath()
                    + "/manageStudents'>"
                    + "Back to Students"
                    + "</a></p>"
            );
        }
    }
}