<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Notifications</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="notifications-page">


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
                View updates related to your practical submissions.
            </p>

        </div>


        <div class="notifications-card">

            <%

                ResultSet rs =
                        (ResultSet) request.getAttribute("notifications");

                boolean hasNotifications = false;

                while (rs != null && rs.next()) {

                    hasNotifications = true;

            %>


            <div class="notification-item">

                <div class="notification-icon">
                    🔔
                </div>

                <div class="notification-content">

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


            <div class="no-notifications">

                <div class="no-notification-icon">
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


        <!-- Back -->

        <div class="page-bottom">

            <a href="${pageContext.request.contextPath}/studentDashboard">
                ← Back to Dashboard
            </a>

        </div>

    </div>


</body>

</html>