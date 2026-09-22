
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Student Dashboard</title>
    </head>
    <body>
        <h1>PracTrack-Student Dashboard</h1>
        <h3>Welcome, <%= session.getAttribute("userName") %></h3>
        <hr>
        <h2>My Practicals</h2>
        
        <a href="${pageContext.request.contextPath}/practicals">
               View Practicals
        </a>
               
        <table border="1" cellpadding="10">
            <tr>
                <th>Practical</th>
                <th>Title</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <tr>
                <td>1</td>
                <td>Introduction to Servlet</td>
                <td>Checked</td>
                <td>View</td>
                
            </tr>
            <tr>
                <td>2</td>
                <td>JDBC Connection</td>
                <td>Submitted</td>
                <td>View</td>
             </tr>   
             <tr>
                <td>3</td>
                <td>Session Management</td>
                <td>Resubmit</td>
                <td>Submit Again</td>
             </tr>   
             <tr>
                <td>4</td>
                <td>JSP</td>
                <td>Not Submitted</td>
                <td>Submit</td>
             </tr>   
        </table>
               <hr>

<a href="${pageContext.request.contextPath}/logout">
    Logout
</a>
    </body>
</html>
