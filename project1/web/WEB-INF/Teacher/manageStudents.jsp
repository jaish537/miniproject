<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Manage Students</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="manage-students-page">

    <div class="page-header">

        <div class="page-brand">

            <span class="page-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="page-separator">—</span>

            <span class="page-title">
                Manage Students
            </span>

        </div>

    </div>


    <div class="manage-students-content">

        <div class="manage-students-card">

            <div class="submit-icon">
                👨‍🎓
            </div>

            <h2>Students List</h2>

            <p class="manage-students-description">
                View, edit or remove student entries.
            </p>


            <div style="overflow-x:auto;">

                <table class="manage-students-table">

                    <thead>

                        <tr>

                            <th>Name</th>

                            <th>Roll No</th>

                            <th>Email</th>

                            <th>Semester</th>

                            <th>Section</th>

                            <th>Action</th>

                        </tr>

                    </thead>


                    <tbody>

                        <%
                        java.sql.ResultSet students =
                                (java.sql.ResultSet)
                                request.getAttribute("students");

                        boolean hasStudents = false;

                        while (students != null &&
                               students.next()) {

                            hasStudents = true;
                        %>

                        <tr>

                            <td class="manage-student-name">
                                <%= students.getString("name") %>
                            </td>

                            <td>
                                <%= students.getString("roll_no") %>
                            </td>

                            <td class="manage-student-email">
                                <%= students.getString("email") %>
                            </td>

                            <td>
                                <%= students.getInt("semester") %>
                            </td>

                            <td>
                                <%= students.getString("section") %>
                            </td>

                            <td class="manage-student-actions">

                                <a href="${pageContext.request.contextPath}/editStudent?studentId=<%= students.getLong("student_id") %>"
                                   class="manage-edit-button">
                                    ✏️ Edit
                                </a>

                                <a href="${pageContext.request.contextPath}/deleteStudent?studentId=<%= students.getLong("student_id") %>"
                                   class="manage-delete-button"
                                   onclick="return confirm('Are you sure you want to delete this student?');">
                                    🗑️ Delete
                                </a>

                            </td>

                        </tr>

                        <%
                        }

                        if (!hasStudents) {
                        %>

                        <tr>

                            <td colspan="6"
                                style="padding:25px;
                                       text-align:center;
                                       color:#718397;">

                                No students found.

                            </td>

                        </tr>

                        <%
                        }
                        %>

                    </tbody>

                </table>

            </div>


            <div class="manage-students-back">

                <a href="${pageContext.request.contextPath}/teacherDashboard">
                    ← Back to Dashboard
                </a>

            </div>

        </div>

    </div>

</body>

</html>