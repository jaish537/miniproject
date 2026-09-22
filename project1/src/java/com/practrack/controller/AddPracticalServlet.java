package com.practrack.controller;

import com.practrack.dao.PracticalDAO;
import java.io.IOException;
import java.sql.Date;
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

        String subjectId = request.getParameter("subjectId");
        String practicalNo = request.getParameter("practicalNo");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String deadline = request.getParameter("deadline");

        try {

            PracticalDAO dao = new PracticalDAO();

            int result = dao.addPractical(
                    Long.parseLong(subjectId),
                    Integer.parseInt(practicalNo),
                    title,
                    description,
                    Date.valueOf(deadline)
            );

            if (result > 0) {

                request.getRequestDispatcher(
                        "/WEB-INF/Teacher/dashboard.jsp"
                ).forward(request, response);

            } else {

                response.getWriter().println(
                        "<h2>Practical could not be added</h2>"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(
                    "<h2>Error adding practical</h2>"
            );

            response.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
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