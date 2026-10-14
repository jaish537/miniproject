<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.practrack.dao.SubmissionDAO"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Available Practicals</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="practicals-page">

    <!-- Header -->

    <div class="page-header">

        <div class="page-brand">

            <span class="page-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="page-separator">—</span>

            <span class="page-title">
                Available Practicals
            </span>

        </div>

    </div>


    <!-- Main Content -->

    <div class="page-content">

        <div class="content-heading">

            <h2>📚 Available Practicals</h2>

            <p>
                View practical assignments and submit your work.
            </p>

        </div>


        <div class="practical-table-card">

            <table>

                <tr>

                    <th>Practical No.</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Deadline</th>
                    <th>Action</th>

                </tr>


                <%

                    ResultSet rs =
                            (ResultSet) request.getAttribute("practicals");

                    Object userIdObject =
                            session.getAttribute("userId");

                    long studentId =
                            userIdObject != null
                            ? Long.parseLong(userIdObject.toString())
                            : 0;

                    SubmissionDAO submissionDAO =
                            new SubmissionDAO();

                    java.sql.Date today =
                            new java.sql.Date(
                                    System.currentTimeMillis()
                            );

                    while (rs != null && rs.next()) {

                        long practicalId =
                                rs.getLong("practical_id");

                        java.sql.Date deadline =
                                rs.getDate("deadline");

                        String submissionStatus =
                                submissionDAO.getSubmissionStatus(
                                        studentId,
                                        practicalId
                                );

                %>


                <tr>

                    <td>
                        <span class="practical-number">
                            <%= rs.getInt("practical_no") %>
                        </span>
                    </td>


                    <td>
                        <strong>
                            <%= rs.getString("title") %>
                        </strong>
                    </td>


                    <td>
                        <%= rs.getString("description") %>
                    </td>


                    <td>
                        <%= deadline %>
                    </td>


                    <td>

                        <%

                            /*
                             * Already checked
                             */
                            if ("CHECKED".equalsIgnoreCase(
                                    submissionStatus)) {

                        %>

                            <span>
                                ✓ Checked
                            </span>

                        <%

                            /*
                             * Teacher requested resubmission
                             */
                            } else if ("RESUBMIT".equalsIgnoreCase(
                                    submissionStatus)) {

                        %>

                            <a class="submit-button"
                               href="<%= request.getContextPath() %>/submitPractical?practicalId=<%= practicalId %>">

                                Resubmit

                            </a>

                        <%

                            /*
                             * Student already submitted
                             */
                            } else if ("SUBMITTED".equalsIgnoreCase(
                                    submissionStatus)) {

                        %>

                            <span>
                                Submitted
                            </span>

                        <%

                            /*
                             * Deadline has passed
                             */
                            } else if (deadline != null &&
                                       today.after(deadline)) {

                        %>

                            <span>
                                Deadline Passed
                            </span>

                        <%

                            /*
                             * No submission and deadline is active
                             */
                            } else {

                        %>

                            <a class="submit-button"
                               href="<%= request.getContextPath() %>/submitPractical?practicalId=<%= practicalId %>">

                                Submit

                            </a>

                        <%

                            }

                        %>

                    </td>

                </tr>


                <%

                    }

                %>

            </table>

        </div>


        <!-- Back -->

        <div class="page-bottom">

            <a href="${pageContext.request.contextPath}/studentDashboard">
                ← Back to Dashboard
            </a>

        </div>

    </div>

</body>

</html>