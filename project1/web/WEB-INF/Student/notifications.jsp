<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Notifications</title>
    </head>

    <body>

        <h1>Notifications</h1>

        <ul>

            <%
                ResultSet rs =
                    (ResultSet) request.getAttribute("notifications");

                while (rs != null && rs.next()) {
            %>

            <li>
                <%= rs.getString("message") %>
                -
                <%= rs.getTimestamp("created_at") %>
            </li>

            <%
                }
            %>

        </ul>

    </body>
</html>