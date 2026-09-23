<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Student Dashboard</title>
    </head>

    <body>

        <h1>PracTrack-Student Dashboard</h1>

        <h3>
            Welcome, <%= session.getAttribute("userName") %>
        </h3>

        <hr>

        <h2>My Practicals</h2>

        <a href="${pageContext.request.contextPath}/practicals">
            View Practicals
        </a>

        <br><br>

        <a href="${pageContext.request.contextPath}/submissionStatus">
            Submission Status
        </a>

        <br><br>

        <a href="${pageContext.request.contextPath}/notifications">
            Notifications
        </a>

        <br><br>

        <table border="1" cellpadding="10">

            <tr>
                <th>Practical</th>
                <th>Title</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <%
                ResultSet rs =
                        (ResultSet) request.getAttribute("practicals");

                while (rs != null && rs.next()) {

                    int practicalNo =
                            rs.getInt("practical_no");

                    String title =
                            rs.getString("title");

                    String status =
                            rs.getString("status");

                    long submissionId =
                            rs.getLong("submission_id");

                    if (rs.wasNull()) {
                        status = "NOT_SUBMITTED";
                    }
            %>

            <tr>

                <td><%= practicalNo %></td>

                <td><%= title %></td>

                <td><%= status %></td>

                <td>

                    <%
                        if ("NOT_SUBMITTED".equals(status)) {
                    %>

                        <a href="<%= request.getContextPath() %>/submitPractical">
                            Submit
                        </a>

                    <%
                        } else if ("RESUBMIT".equals(status)) {
                    %>

                        <a href="<%= request.getContextPath() %>/submitPractical">
                            Submit Again
                        </a>

                    <%
                        } else {
                    %>

                        <a href="<%= request.getContextPath() %>/submissionStatus">
                            View
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

        <hr>

        <a href="${pageContext.request.contextPath}/logout">
            Logout
        </a>

    </body>
</html>