package com.practrack.controller;

import com.practrack.dao.PracticalDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deletePractical")
public class DeletePracticalServlet extends HttpServlet {

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

            int result =
                    dao.deletePractical(practicalId);

            if (result > 0) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/managePracticals"
                );

            } else {

                throw new Exception(
                        "Practical could not be deleted."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error deleting practical</h2>"
            );

            response.getWriter().println(
                    "<p>" + e.getMessage() + "</p>"
            );
        }
    }
}