<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>Submission Status</title>
    </head>

    <body>

        <h1>Submission Status</h1>

        <table border="1" cellpadding="10">

            <tr>
                <th>Practical</th>
                <th>File Name</th>
                <th>Submitted On</th>
                <th>Status</th>
            </tr>

            <%
                ResultSet rs =
                    (ResultSet) request.getAttribute("submissions");

                while (rs != null && rs.next()) {
            %>

            <tr>

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

            </tr>

            <%
                }
            %>

        </table>

        <br>

        <a href="<%= request.getContextPath() %>/practicals">
            Back to Practicals
        </a>

    </body>
</html>