package com.practrack.controller;

import com.practrack.dao.StudentDAO;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {

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

            ResultSet rs =
                    dao.getStudentById(studentId);

            if (rs.next()) {

                request.setAttribute(
                        "studentId",
                        studentId
                );

                request.setAttribute(
                        "name",
                        rs.getString("name")
                );

                request.setAttribute(
                        "rollNo",
                        rs.getString("roll_no")
                );

                request.setAttribute(
                        "email",
                        rs.getString("email")
                );

                request.setAttribute(
                        "semester",
                        rs.getInt("semester")
                );

                request.setAttribute(
                        "section",
                        rs.getString("section")
                );

                request.getRequestDispatcher(
                        "/WEB-INF/Teacher/editStudent.jsp"
                ).forward(
                        request,
                        response
                );

            } else {

                throw new Exception(
                        "Student not found."
                );
            }

            rs.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error loading student</h2>"
            );

            response.getWriter().println(
                    "<p>"
                    + e.getMessage()
                    + "</p>"
            );
        }
    }


    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        try {

            long studentId =
                    Long.parseLong(
                            request.getParameter(
                                    "studentId"
                            )
                    );

            String name =
                    request.getParameter("name");

            String rollNo =
                    request.getParameter("rollNo");

            String email =
                    request.getParameter("email");

            int semester =
                    Integer.parseInt(
                            request.getParameter(
                                    "semester"
                            )
                    );

            String section =
                    request.getParameter("section");


            StudentDAO dao =
                    new StudentDAO();

            int result =
                    dao.updateStudent(
                            studentId,
                            name,
                            rollNo,
                            email,
                            semester,
                            section
                    );


            if (result > 0) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/manageStudents"
                );

            } else {

                throw new Exception(
                        "Student could not be updated."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error updating student</h2>"
            );

            response.getWriter().println(
                    "<p>"
                    + e.getMessage()
                    + "</p>"
            );
        }
    }
}