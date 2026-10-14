package com.practrack.controller;

import com.practrack.dao.StudentDAO;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/manageStudents")
public class ManageStudentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            StudentDAO dao =
                    new StudentDAO();

            ResultSet rs =
                    dao.getAllStudents();

            request.setAttribute(
                    "students",
                    rs
            );

            request.getRequestDispatcher(
                    "/WEB-INF/Teacher/manageStudents.jsp"
            ).forward(
                    request,
                    response
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error loading students</h2>"
            );

            response.getWriter().println(
                    "<p>"
                    + e.getMessage()
                    + "</p>"
            );
        }
    }
}