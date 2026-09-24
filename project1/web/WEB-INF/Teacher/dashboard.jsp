<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Teacher Dashboard</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="teacher-dashboard">


    <!-- Header -->

    <div class="teacher-header">

        <div class="teacher-brand">

            <span class="teacher-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="teacher-separator">—</span>

            <span class="teacher-title">
                Teacher Dashboard
            </span>

        </div>


        <div class="teacher-profile">

            👤 <%= session.getAttribute("userName") %>

        </div>

    </div>



    <!-- Welcome -->

    <div class="teacher-welcome">

        <div class="teacher-welcome-icon">
            👋
        </div>

        <div>

            <h2>
                Welcome, <%= session.getAttribute("userName") %>
            </h2>

            <p>
                Manage practicals, review student submissions
                and track submission activity here.
            </p>

        </div>

    </div>



    <!-- Overview -->

    <div class="teacher-overview">

        <h2>📊 &nbsp; Practical Overview</h2>


        <div class="teacher-stats">


            <div class="teacher-stat-card">

                <div class="teacher-stat-icon">
                    👨‍🎓
                </div>

                <div>

                    <span>Total Students</span>

                    <strong>
                        <%= request.getAttribute("totalStudents") %>
                    </strong>

                </div>

            </div>



            <div class="teacher-stat-card">

                <div class="teacher-stat-icon">
                    📤
                </div>

                <div>

                    <span>Submitted</span>

                    <strong>
                        <%= request.getAttribute("submitted") %>
                    </strong>

                </div>

            </div>



            <div class="teacher-stat-card">

                <div class="teacher-stat-icon">
                    ✓
                </div>

                <div>

                    <span>Checked</span>

                    <strong>
                        <%= request.getAttribute("checked") %>
                    </strong>

                </div>

            </div>



            <div class="teacher-stat-card">

                <div class="teacher-stat-icon">
                    ⏳
                </div>

                <div>

                    <span>Pending</span>

                    <strong>
                        <%= request.getAttribute("pending") %>
                    </strong>

                </div>

            </div>


        </div>

    </div>



    <!-- Teacher Actions -->

    <div class="teacher-actions">

        <h2>⚡ &nbsp; Quick Actions</h2>


        <div class="teacher-action-cards">


            <a class="teacher-action-card"
               href="${pageContext.request.contextPath}/addPractical">

                <span class="teacher-action-icon">
                    ➕
                </span>

                <span>

                    <strong>Add Practical</strong>

                    <small>
                        Create a new practical assignment
                    </small>

                </span>

            </a>



            <a class="teacher-action-card"
               href="${pageContext.request.contextPath}/submissions">

                <span class="teacher-action-icon">
                    📋
                </span>

                <span>

                    <strong>Student Submissions</strong>

                    <small>
                        Review submitted practical files
                    </small>

                </span>

            </a>



            <a class="teacher-action-card"
               href="${pageContext.request.contextPath}/notifications">

                <span class="teacher-action-icon">
                    🔔
                </span>

                <span>

                    <strong>Notifications</strong>

                    <small>
                        View latest submission updates
                    </small>

                </span>

            </a>


        </div>

    </div>



    <!-- Recent Submissions -->

    <div class="teacher-recent">

        <h2>📄 &nbsp; Recent Submissions</h2>

        <p>
            View and review student practical submissions
            from the Student Submissions section.
        </p>

        <a href="${pageContext.request.contextPath}/submissions">
            View Student Submissions
        </a>

    </div>



    <!-- Logout -->

    <div class="teacher-logout">

        <a href="${pageContext.request.contextPath}/logout">
            ⇥ &nbsp; Logout
        </a>

    </div>


</body>

</html>