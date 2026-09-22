package com.practrack.controller;

import com.practrack.dao.SubmissionDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/submitPractical")
public class SubmitPracticalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/Student/submitPractical.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String practicalId = request.getParameter("practicalId");
        String fileName = request.getParameter("fileName");

        try {

            HttpSession session = request.getSession();

            long studentId =
                    Long.parseLong(
                            session.getAttribute("userId").toString()
                    );

            SubmissionDAO dao = new SubmissionDAO();

            int result = dao.submitPractical(
                    studentId,
                    Long.parseLong(practicalId),
                    fileName
            );

            if (result > 0) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/submissionStatus"
                );

            } else {

                response.setContentType(
                        "text/html;charset=UTF-8"
                );

                response.getWriter().println(
                        "<h2>Submission failed</h2>"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error submitting practical</h2>"
            );

            response.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
            );
        }
    }
}