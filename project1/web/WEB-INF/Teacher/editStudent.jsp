<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Edit Student</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="edit-student-page">

    <div class="page-header">

        <div class="page-brand">

            <span class="page-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="page-separator">—</span>

            <span class="page-title">
                Edit Student
            </span>

        </div>

    </div>


    <div class="submit-content">

        <div class="submit-card">

            <div class="submit-icon">
                ✏️
            </div>

            <h2>Edit Student Details</h2>

            <p class="submit-description">
                Update the student's information below.
            </p>


            <form action="${pageContext.request.contextPath}/editStudent"
                  method="post">


                <input type="hidden"
                       name="studentId"
                       value="<%= request.getAttribute("studentId") %>">


                <label for="name">
                    Name
                </label>

                <input type="text"
                       id="name"
                       name="name"
                       value="<%= request.getAttribute("name") %>"
                       required>


                <label for="rollNo">
                    Roll No
                </label>

                <input type="text"
                       id="rollNo"
                       name="rollNo"
                       value="<%= request.getAttribute("rollNo") %>"
                       required>


                <label for="email">
                    Email
                </label>

                <input type="email"
                       id="email"
                       name="email"
                       value="<%= request.getAttribute("email") %>"
                       required>


                <label for="semester">
                    Semester
                </label>

                <input type="number"
                       id="semester"
                       name="semester"
                       value="<%= request.getAttribute("semester") %>"
                       min="1"
                       max="8"
                       required>


                <label for="section">
                    Section
                </label>

                <input type="text"
                       id="section"
                       name="section"
                       value="<%= request.getAttribute("section") %>"
                       required>


                <button type="submit"
                        class="submit-practical-button">
                    💾 Update Student
                </button>

            </form>


            <div class="submit-back">

                <a href="${pageContext.request.contextPath}/manageStudents">
                    ← Back to Students
                </a>

            </div>

        </div>

    </div>

</body>

</html>