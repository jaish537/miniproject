<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Student Submissions</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="teacher-submissions-page">


    <!-- Header -->

    <div class="page-header">

        <div class="page-brand">

            <span class="page-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="page-separator">—</span>

            <span class="page-title">
                Student Submissions
            </span>

        </div>

    </div>


    <!-- Main Content -->

    <div class="page-content">

        <div class="content-heading">

            <h2>📋 Student Submissions</h2>

            <p>
                View and review practical files submitted by students.
            </p>

        </div>


        <div class="submissions-table-card">

            <table>

                <tr>

                    <th>Student Name</th>
                    <th>Roll No</th>
                    <th>Practical</th>
                    <th>File Name</th>
                    <th>Submitted On</th>
                    <th>Status</th>
                    <th>Action</th>

                </tr>


                <%
                    ResultSet rs =
                        (ResultSet) request.getAttribute("submissions");

                    while (rs != null && rs.next()) {
                %>

                <tr>

                    <td>
                        <strong>
                            <%= rs.getString("student_name") %>
                        </strong>
                    </td>

                    <td>
                        <span class="roll-number">
                            <%= rs.getString("roll_no") %>
                        </span>
                    </td>

                    <td>
                        <%= rs.getString("title") %>
                    </td>

                    <td>
                        <span class="file-name">
                            📄 <%= rs.getString("file_name") %>
                        </span>
                    </td>

                    <td>
                        <%= rs.getTimestamp("submitted_at") %>
                    </td>

                    <td>

                        <%
                            String status = rs.getString("status");
                        %>

                        <span class="status-badge <%= status.toLowerCase() %>">
                            <%= status %>
                        </span>

                    </td>

                    <td>

                        <a class="review-button"
                           href="<%= request.getContextPath() %>/reviewSubmission?submissionId=<%= rs.getLong("submission_id") %>">

                            Review

                        </a>

                    </td>

                </tr>

                <%
                    }
                %>

            </table>

        </div>


        <div class="page-bottom">

            <a href="${pageContext.request.contextPath}/teacherDashboard">
                ← Back to Dashboard
            </a>

        </div>

    </div>

</body>

</html>