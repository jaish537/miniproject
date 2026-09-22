package com.practrack.controller;

import com.practrack.dao.SubmissionDAO;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/submissionStatus")
public class SubmissionStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();

            long studentId =
                    Long.parseLong(
                            session.getAttribute("userId").toString()
                    );

            SubmissionDAO dao = new SubmissionDAO();

            ResultSet rs =
                    dao.getSubmissionsByStudent(studentId);

            request.setAttribute("submissions", rs);

            request.getRequestDispatcher(
                    "/WEB-INF/Student/submissionStatus.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error loading submission status</h2>"
            );

            response.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
            );
        }
    }
}