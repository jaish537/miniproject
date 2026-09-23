<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>Student Submissions</title>
    </head>

    <body>

        <h1>Student Submissions</h1>

        <table border="1" cellpadding="10">

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
                    <%= rs.getString("student_name") %>
                </td>

                <td>
                    <%= rs.getString("roll_no") %>
                </td>

                <td>
                    <%= rs.getString("title") %>
                </td>

                <td>
                    <%= rs.getString("file_name") %>
                </td>

                <td>
                    <%= rs.getTimestamp("submitted_at") %>
                </td>

                <td>
                    <%= rs.getString("status") %>
                </td>

                <td>
                    <a href="<%= request.getContextPath() %>/reviewSubmission?submissionId=<%= rs.getLong("submission_id") %>">
                        Review
                    </a>
                </td>

            </tr>

            <%
                }
            %>

        </table>

        <br>

        <a href="<%= request.getContextPath() %>/teacherDashboard">
            Back to Dashboard
        </a>

    </body>
</html>