package com.practrack.controller;

import com.practrack.dao.SubmissionDAO;
import com.practrack.dao.TeacherDAO;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/reviewSubmission")
public class ReviewSubmissionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("submissionId");

        if (id == null) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Submission ID missing"
            );
            return;
        }

        try {

            SubmissionDAO dao = new SubmissionDAO();

            ResultSet rs =
                    dao.getSubmissionById(Long.parseLong(id));

            if (rs.next()) {

                request.setAttribute("submissionId",
                        rs.getLong("submission_id"));

                request.setAttribute("studentName",
                        rs.getString("student_name"));

                request.setAttribute("practicalTitle",
                        rs.getString("practical_title"));

                request.setAttribute("submittedOn",
                        rs.getTimestamp("submitted_at"));

                request.setAttribute("fileName",
                        rs.getString("file_name"));

                request.setAttribute("status",
                        rs.getString("status"));

                request.getRequestDispatcher(
                        "/WEB-INF/Teacher/reviewSubmission.jsp"
                ).forward(request, response);

            } else {

                response.getWriter().println(
                        "<h2>Submission not found</h2>"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error loading submission</h2>"
            );

            response.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String submissionId =
                request.getParameter("submissionId");

        String status =
                request.getParameter("status");

        String remarks =
                request.getParameter("remark");

        try {

            HttpSession session = request.getSession();

            long teacherId =
                    Long.parseLong(
                            session.getAttribute("userId").toString()
                    );

            TeacherDAO teacherDAO = new TeacherDAO();
            SubmissionDAO submissionDAO = new SubmissionDAO();

            teacherDAO.saveReview(
                    Long.parseLong(submissionId),
                    teacherId,
                    status,
                    remarks
            );

            submissionDAO.updateStatus(
                    Long.parseLong(submissionId),
                    status
            );

            response.sendRedirect(
                    request.getContextPath()
                    + "/submissions"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error saving review</h2>"
            );

            response.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
            );
        }
    }
}