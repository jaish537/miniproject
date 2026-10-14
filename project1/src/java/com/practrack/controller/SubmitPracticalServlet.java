package com.practrack.controller;

import com.practrack.dao.NotificationDAO;
import com.practrack.dao.SubmissionDAO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/submitPractical")
@MultipartConfig(
    maxFileSize = 10 * 1024 * 1024,
    maxRequestSize = 12 * 1024 * 1024
)
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

        try {

            HttpSession session = request.getSession();

            Object userIdObject =
                    session.getAttribute("userId");

            if (userIdObject == null) {
                response.sendRedirect(
                        request.getContextPath() + "/login.jsp"
                );
                return;
            }

            long studentId =
                    Long.parseLong(userIdObject.toString());

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

            SubmissionDAO dao =
                    new SubmissionDAO();
            
            java.sql.Date deadline =
        new com.practrack.dao.PracticalDAO()
        .getDeadlineByPracticalId(practicalId);

if (deadline == null) {
    throw new Exception(
            "Deadline is not available for this practical."
    );
}

java.sql.Date today =
        new java.sql.Date(System.currentTimeMillis());

if (today.after(deadline)) {
    throw new Exception(
            "Submission deadline has passed. " +
            "You cannot submit this practical now."
    );
}
            /*
             * Check whether this student has already
             * submitted this practical.
             */
            String existingStatus =
                    dao.getSubmissionStatus(
                            studentId,
                            practicalId
                    );

            if (existingStatus != null) {

                if ("CHECKED".equalsIgnoreCase(existingStatus)) {

                    throw new Exception(
                            "This practical has already been checked. " +
                            "You cannot submit it again."
                    );
                }

                if ("SUBMITTED".equalsIgnoreCase(existingStatus)) {

                    throw new Exception(
                            "You have already submitted this practical. " +
                            "Wait for the teacher's review."
                    );
                }

                if (!"RESUBMIT".equalsIgnoreCase(existingStatus)) {

                    throw new Exception(
                            "This practical cannot be submitted again."
                    );
                }
            }

            Part filePart =
                    request.getPart("file");

            if (filePart == null) {
                throw new Exception(
                        "No file was uploaded."
                );
            }

            if (filePart.getSize() == 0) {
                throw new Exception(
                        "Please select a PDF file."
                );
            }

            String originalFileName =
                    filePart.getSubmittedFileName();

            if (originalFileName == null ||
                originalFileName.trim().isEmpty()) {

                throw new Exception(
                        "File name could not be read."
                );
            }

            if (!originalFileName
                    .toLowerCase()
                    .endsWith(".pdf")) {

                throw new Exception(
                        "Only PDF files are allowed."
                );
            }

            if (filePart.getSize()
                    > 10 * 1024 * 1024) {

                throw new Exception(
                        "PDF size must be less than 10 MB."
                );
            }

            String uploadPath =
                    "C:\\PracTrackUploads";

            File uploadFolder =
                    new File(uploadPath);

            if (!uploadFolder.exists()) {

                boolean created =
                        uploadFolder.mkdirs();

                if (!created &&
                    !uploadFolder.exists()) {

                    throw new Exception(
                            "Could not create upload folder."
                    );
                }
            }

            String cleanFileName =
                    new File(originalFileName)
                    .getName();

            String savedFileName =
                    UUID.randomUUID().toString()
                    + "_" + cleanFileName;

            File destination =
                    new File(
                            uploadFolder,
                            savedFileName
                    );

            try (InputStream inputStream =
                         filePart.getInputStream()) {

                Files.copy(
                        inputStream,
                        destination.toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                );
            }

            int result;

            if ("RESUBMIT".equalsIgnoreCase(existingStatus)) {

                /*
                 * For RESUBMIT, update the existing
                 * submission instead of creating
                 * another duplicate record.
                 */
                result =
                        dao.resubmitPractical(
                                studentId,
                                practicalId,
                                savedFileName
                        );

            } else {

                result =
                        dao.submitPractical(
                                studentId,
                                practicalId,
                                savedFileName
                        );
            }

            if (result > 0) {

                NotificationDAO notificationDAO =
                        new NotificationDAO();

                notificationDAO.addNotification(
                        "TEACHER",
                        1,
                        "New practical submission received."
                );

                response.sendRedirect(
                        request.getContextPath()
                        + "/submissionStatus"
                );

            } else {

                if (destination.exists()) {
                    destination.delete();
                }

                throw new Exception(
                        "Submission could not be saved."
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