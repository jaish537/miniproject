<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>Practicals</title>
    </head>

    <body>

        <h1>Available Practicals</h1>

        <p>
            Here you can view all practicals assigned by teacher.
        </p>

        <hr>

        <table border="1" cellpadding="10">

            <tr>
                <th>Practical ID</th>
                <th>Practical No</th>
                <th>Title</th>
                <th>Description</th>
                <th>Deadline</th>
                <th>Action</th>
            </tr>

            <%
                ResultSet rs =
                    (ResultSet) request.getAttribute("practicals");

                while (rs != null && rs.next()) {
            %>

            <tr>
                <td><%= rs.getLong("practical_id") %></td>

                <td><%= rs.getInt("practical_no") %></td>

                <td><%= rs.getString("title") %></td>

                <td><%= rs.getString("description") %></td>

                <td><%= rs.getDate("deadline") %></td>

                <td>
                    <a href="<%= request.getContextPath() %>/submitPractical">
                        Submit
                    </a>
                </td>
            </tr>

            <%
                }
            %>

        </table>

    </body>
</html>