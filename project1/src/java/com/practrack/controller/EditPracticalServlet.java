package com.practrack.controller;

import com.practrack.dao.PracticalDAO;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editPractical")
public class EditPracticalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String practicalIdText =
                    request.getParameter("practicalId");

            if (practicalIdText == null ||
                practicalIdText.trim().isEmpty()) {

                throw new Exception(
                        "Practical ID is required."
                );
            }

            long practicalId =
                    Long.parseLong(practicalIdText);

            PracticalDAO dao =
                    new PracticalDAO();

            java.sql.ResultSet rs =
                    dao.getPracticalById(practicalId);

            if (rs.next()) {

                request.setAttribute(
                        "practicalId",
                        practicalId
                );

                request.setAttribute(
                        "practicalNo",
                        rs.getInt("practical_no")
                );

                request.setAttribute(
                        "title",
                        rs.getString("title")
                );

                request.setAttribute(
                        "description",
                        rs.getString("description")
                );

                request.setAttribute(
                        "deadline",
                        rs.getDate("deadline")
                );

                request.getRequestDispatcher(
                        "/WEB-INF/Teacher/editPractical.jsp"
                ).forward(request, response);

            } else {

                throw new Exception(
                        "Practical not found."
                );
            }

            rs.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error loading practical</h2>"
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

        try {

            long practicalId =
                    Long.parseLong(
                            request.getParameter(
                                    "practicalId"
                            )
                    );

            int practicalNo =
                    Integer.parseInt(
                            request.getParameter(
                                    "practicalNo"
                            )
                    );

            String title =
                    request.getParameter("title");

            String description =
                    request.getParameter("description");

            String deadlineText =
                    request.getParameter("deadline");

            Date deadline =
                    Date.valueOf(deadlineText);

            PracticalDAO dao =
                    new PracticalDAO();

            int result =
                    dao.updatePractical(
                            practicalId,
                            practicalNo,
                            title,
                            description,
                            deadline
                    );

            if (result > 0) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/teacherDashboard"
                );

            } else {

                throw new Exception(
                        "Practical could not be updated."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error updating practical</h2>"
            );

            response.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
            );
        }
    }
}