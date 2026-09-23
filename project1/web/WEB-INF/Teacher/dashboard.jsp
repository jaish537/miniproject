<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Teacher Dashboard</title>
    </head>

    <body>

        <h1>Practrack-Teacher Dashboard</h1>

        <h3>
            Welcome, <%= session.getAttribute("userName") %>
        </h3>

        <hr>

        <h2>Practical Overview</h2>

        <table border="1" cellpadding="10">

            <tr>
                <th>Category</th>
                <th>Count</th>
            </tr>

            <tr>
                <td>Total Students</td>
                <td><%= request.getAttribute("totalStudents") %></td>
            </tr>

            <tr>
                <td>Submitted</td>
                <td><%= request.getAttribute("submitted") %></td>
            </tr>

            <tr>
                <td>Checked</td>
                <td><%= request.getAttribute("checked") %></td>
            </tr>

            <tr>
                <td>Pending</td>
                <td><%= request.getAttribute("pending") %></td>
            </tr>

        </table>

        <br>
        <hr>

        <h2>Teacher Actions</h2>

        <a href="${pageContext.request.contextPath}/addPractical">
            Add Practical
        </a>

        <br><br>

        <a href="${pageContext.request.contextPath}/submissions">
            View Student Submissions
        </a>

        <br><br>

        <a href="${pageContext.request.contextPath}/notifications">
            Notifications
        </a>

        <hr>

        <h2>Recent Submissions</h2>

        <p>
            View detailed student submissions using
            <a href="${pageContext.request.contextPath}/submissions">
                View Student Submissions
            </a>.
        </p>

        <hr>

        <a href="${pageContext.request.contextPath}/logout">
            Logout
        </a>

    </body>
</html>