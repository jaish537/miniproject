package com.practrack.controller;

import com.practrack.dao.SubmissionDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewPdf")
public class ViewPdfServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String submissionId =
                    request.getParameter("submissionId");

            if (submissionId == null ||
                submissionId.trim().isEmpty()) {

                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Submission ID is required."
                );
                return;
            }

            // Get file name from database
            SubmissionDAO dao = new SubmissionDAO();

            String fileName =
                    dao.getFileNameBySubmissionId(
                            Long.parseLong(submissionId)
                    );

            if (fileName == null ||
                fileName.trim().isEmpty()) {

                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "PDF file not found."
                );
                return;
            }

            // PDF location
            File pdfFile =
                    new File(
                            "C:\\PracTrackUploads",
                            new File(fileName).getName()
                    );

            if (!pdfFile.exists()) {

                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "PDF file not found on server."
                );
                return;
            }

            // Tell browser this is a PDF
            response.setContentType("application/pdf");

            response.setHeader(
                    "Content-Disposition",
                    "inline; filename=\"" +
                    pdfFile.getName() +
                    "\""
            );

            response.setContentLengthLong(
                    pdfFile.length()
            );

            // Send PDF to browser
            try (FileInputStream input =
                         new FileInputStream(pdfFile);
                 OutputStream output =
                         response.getOutputStream()) {

                byte[] buffer = new byte[8192];

                int bytesRead;

                while ((bytesRead =
                        input.read(buffer)) != -1) {

                    output.write(
                            buffer,
                            0,
                            bytesRead
                    );
                }

                output.flush();
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Unable to open PDF."
            );
        }
    }
}