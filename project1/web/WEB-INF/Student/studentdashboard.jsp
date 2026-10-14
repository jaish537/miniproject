<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>

<head>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

    <title>Student Dashboard</title>

</head>

<body class="student-dashboard">

    <!-- Header -->

    <div class="student-header">

        <div class="brand-title">

            <span class="brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="header-separator">—</span>

            <span class="dashboard-title">
                Student Dashboard
            </span>

        </div>

        <div class="student-profile">

            👤 <%= session.getAttribute("userName") %>

        </div>

    </div>


    <!-- Welcome Section -->

    <div class="welcome-section">

        <div class="welcome-icon">
            👋
        </div>

        <div class="welcome-content">

            <h2>
                Welcome, <%= session.getAttribute("userName") %>
            </h2>

            <p>
                Manage your practical submissions and track their status here.
            </p>

        </div>

    </div>
            <%
String latestNotification =
        (String) request.getAttribute("latestNotification");

if (latestNotification != null &&
    !latestNotification.trim().isEmpty()) {
%>

<div class="latest-notification-popup">
    <div class="latest-notification-icon">🔔</div>

    <div class="latest-notification-content">
        <strong>Latest Notification</strong>
        <p><%= latestNotification %></p>

        <a href="${pageContext.request.contextPath}/notifications">
            View All Notifications →
        </a>
    </div>
</div>

<%
}
%>


    <!-- Quick Actions -->

    <div class="student-actions">

        <h2>Quick Actions</h2>

        <div class="action-cards">

            <a class="action-card"
               href="${pageContext.request.contextPath}/practicals">

                <span class="action-icon">📚</span>

                <span>
                    <strong>View Practicals</strong>
                    <small>See all available practicals</small>
                </span>

            </a>


            <a class="action-card"
               href="${pageContext.request.contextPath}/submissionStatus">

                <span class="action-icon">📋</span>

                <span>
                    <strong>Submission Status</strong>
                    <small>Track your submissions</small>
                </span>

            </a>


            <a class="action-card"
               href="${pageContext.request.contextPath}/notifications">

                <span class="action-icon">🔔</span>

                <span>
                    <strong>Notifications</strong>
                    <small>View latest updates</small>
                </span>

            </a>

        </div>

    </div>


    <!-- Practical Status -->

    <div class="practical-section">

        <h2>▤ &nbsp; My Practical Status</h2>

        <table>

            <tr>

                <th>Practical</th>
                <th>Title</th>
                <th>Deadline</th>
                <th>Status</th>
                <th>Action</th>

            </tr>


            <%

                ResultSet rs =
                        (ResultSet) request.getAttribute("practicals");

                java.sql.Date today =
                        new java.sql.Date(
                                System.currentTimeMillis()
                        );

                while (rs != null && rs.next()) {

                    long practicalId =
                            rs.getLong("practical_id");

                    int practicalNo =
                            rs.getInt("practical_no");

                    String title =
                            rs.getString("title");

                    java.sql.Date deadline =
                            rs.getDate("deadline");

                    String status =
                            rs.getString("status");

                    if (status == null) {
                        status = "NOT_SUBMITTED";
                    }

            %>


            <tr>

                <td>
                    <%= practicalNo %>
                </td>


                <td>
                    <%= title %>
                </td>


                <td>
                    <%= deadline %>
                </td>


                <td>

                    <span class="status-badge <%= status.toLowerCase() %>">

                        <%= status %>

                    </span>

                </td>


                <td>

                    <%

                        if ("CHECKED".equalsIgnoreCase(status)) {

                    %>

                        <a href="<%= request.getContextPath() %>/submissionStatus">
                            View
                        </a>

                    <%

                        } else if ("RESUBMIT".equalsIgnoreCase(status)) {

                    %>

                        <a href="<%= request.getContextPath() %>/submitPractical?practicalId=<%= practicalId %>">
                            Submit Again
                        </a>

                    <%

                        } else if ("SUBMITTED".equalsIgnoreCase(status)) {

                    %>

                        <a href="<%= request.getContextPath() %>/submissionStatus">
                            View
                        </a>

                    <%

                        } else if (deadline != null &&
                                   today.after(deadline)) {

                    %>

                        <span>
                            Deadline Passed
                        </span>

                    <%

                        } else {

                    %>

                        <a href="<%= request.getContextPath() %>/submitPractical?practicalId=<%= practicalId %>">
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


    <!-- Logout -->

    <div class="logout-section">

        <a href="${pageContext.request.contextPath}/logout">
            ⇥ &nbsp; Logout
        </a>

    </div>


</body>

</html>