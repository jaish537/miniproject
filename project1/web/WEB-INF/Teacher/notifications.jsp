<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Teacher Notifications</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="teacher-notifications-page">


    <!-- Header -->

    <div class="page-header">

        <div class="page-brand">

            <span class="page-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="page-separator">—</span>

            <span class="page-title">
                Notifications
            </span>

        </div>

    </div>


    <!-- Main Content -->

    <div class="page-content">

        <div class="content-heading">

            <h2>🔔 Notifications</h2>

            <p>
                View updates related to student practical submissions.
            </p>

        </div>


        <div class="teacher-notifications-card">

            <%
                ResultSet rs =
                    (ResultSet) request.getAttribute("notifications");

                boolean hasNotifications = false;

                while (rs != null && rs.next()) {

                    hasNotifications = true;
            %>

            <div class="teacher-notification-item">

                <div class="teacher-notification-icon">
                    🔔
                </div>

                <div class="teacher-notification-content">

                    <p>
                        <%= rs.getString("message") %>
                    </p>

                    <small>
                        <%= rs.getTimestamp("created_at") %>
                    </small>

                </div>

            </div>

            <%
                }

                if (!hasNotifications) {
            %>

            <div class="teacher-no-notifications">

                <div class="teacher-no-notification-icon">
                    ✓
                </div>

                <h3>No New Notifications</h3>

                <p>
                    You don't have any notifications at the moment.
                </p>

            </div>

            <%
                }
            %>

        </div>


        <div class="page-bottom">

            <a href="${pageContext.request.contextPath}/teacherDashboard">
                ← Back to Dashboard
            </a>

        </div>

    </div>

</body>

</html>