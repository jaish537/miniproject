package com.practrack.controller;

import com.practrack.dao.StudentDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/addStudents")
@MultipartConfig(
    maxFileSize = 5 * 1024 * 1024,
    maxRequestSize = 6 * 1024 * 1024
)
public class AddStudentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/Teacher/addStudents.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(
                "text/html;charset=UTF-8"
        );

        try {

            Part csvFile =
                    request.getPart("csvFile");

            if (csvFile == null ||
                csvFile.getSize() == 0) {

                throw new Exception(
                        "Please select a CSV file."
                );
            }

            String fileName =
                    csvFile.getSubmittedFileName();

            if (fileName == null ||
                !fileName.toLowerCase().endsWith(".csv")) {

                throw new Exception(
                        "Only CSV files are allowed."
                );
            }

            StudentDAO dao =
                    new StudentDAO();

            int added = 0;
            int skipped = 0;

            StringBuilder errors =
                    new StringBuilder();

            try (BufferedReader reader =
                    new BufferedReader(
                        new InputStreamReader(
                            csvFile.getInputStream(),
                            StandardCharsets.UTF_8
                        )
                    )) {

                // Skip header
                String line = reader.readLine();

                if (line == null) {
                    throw new Exception(
                            "CSV file is empty."
                    );
                }

                int rowNumber = 1;

                while ((line = reader.readLine()) != null) {

                    rowNumber++;

                    line = line.trim();

                    if (line.isEmpty()) {
                        continue;
                    }

                    String[] data =
                            line.split(",", -1);

                    if (data.length != 5) {

                        skipped++;

                        errors.append(
                                "Row "
                                + rowNumber
                                + ": Expected 5 columns, found "
                                + data.length
                                + "<br>"
                        );

                        continue;
                    }

                    String name =
                            data[0].trim();

                    String rollNo =
                            data[1].trim();

                    String email =
                            data[2].trim();

                    String semesterText =
                            data[3].trim();

                    String section =
                            data[4].trim();

                    if (name.isEmpty() ||
                        rollNo.isEmpty() ||
                        email.isEmpty() ||
                        semesterText.isEmpty() ||
                        section.isEmpty()) {

                        skipped++;

                        errors.append(
                                "Row "
                                + rowNumber
                                + ": One or more fields are empty.<br>"
                        );

                        continue;
                    }

                    int semester;

                    try {

                        semester =
                                Integer.parseInt(
                                        semesterText
                                );

                    } catch (NumberFormatException e) {

                        skipped++;

                        errors.append(
                                "Row "
                                + rowNumber
                                + ": Semester must be a number.<br>"
                        );

                        continue;
                    }

                    try {

                        int result =
                                dao.addStudent(
                                        name,
                                        rollNo,
                                        email,
                                        semester,
                                        section
                                );

                        if (result > 0) {

                            added++;

                        } else {

                            skipped++;

                            errors.append(
                                    "Row "
                                    + rowNumber
                                    + ": Student could not be added.<br>"
                            );
                        }

                    } catch (Exception e) {

                        skipped++;

                        errors.append(
                                "Row "
                                + rowNumber
                                + ": "
                                + e.getMessage()
                                + "<br>"
                        );
                    }
                }
            }

            response.getWriter().println(
                    "<h2>Students Import Completed</h2>"
            );

            response.getWriter().println(
                    "<p>Students added: "
                    + added
                    + "</p>"
            );

            response.getWriter().println(
                    "<p>Rows skipped: "
                    + skipped
                    + "</p>"
            );

            response.getWriter().println(
                    "<p>Temporary password: "
                    + "<b>student123</b>"
                    + "</p>"
            );

            if (errors.length() > 0) {

                response.getWriter().println(
                        "<h3>Details:</h3>"
                );

                response.getWriter().println(
                        "<p>"
                        + errors.toString()
                        + "</p>"
                );
            }

            response.getWriter().println(
                    "<p><a href='"
                    + request.getContextPath()
                    + "/teacherDashboard'>"
                    + "Back to Teacher Dashboard"
                    + "</a></p>"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<h2>Error importing students</h2>"
            );

            response.getWriter().println(
                    "<p>"
                    + e.getMessage()
                    + "</p>"
            );

            response.getWriter().println(
                    "<p><a href='"
                    + request.getContextPath()
                    + "/addStudents'>"
                    + "Back"
                    + "</a></p>"
            );
        }
    }
}