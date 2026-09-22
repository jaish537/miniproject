
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
        <title>Teacher Dashboard</title>
    </head>
    <body>
        <h1>Practrack-Teacher Dashboard</h1>
        <h3>Welcome, <%= session.getAttribute("userName") %></h3>
        <hr>
        <h2>Practical Overview</h2>
        <table border="1" cellpadding="10">
            <tr>
                <th>Category</th>
                <th>Count</th>
            </tr>
            <tr>
                <td>Total Students</td>
                <td>60</td>
            </tr>
             <tr>
                <td>Submitted</td>
                <td>52</td>
            </tr>
             <tr>
                <td>Checked</td>
                <td>40</td>
            </tr>
            <tr>
                <td>Pending</td>
                <td>12</td>
            </tr>

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
    <tr>
        <th>Student</th>
        <th>Practical</th>
        <th>Status</th>
        <th>Action</th>
    </tr>  
    <tr>
        <td>Jaish</td>
        <td>Practical 45</td>
        <td>Submitted</td>
        <td>Check File</td>
    </tr>
      <tr>
        <td>lakshya</td>
        <td>Practical 45</td>
        <td>Submitted</td>
        <td>Check File</td>
    </tr>
</table>
    <hr>

<a href="${pageContext.request.contextPath}/logout">
    Logout
</a>
    </body>
</html>
