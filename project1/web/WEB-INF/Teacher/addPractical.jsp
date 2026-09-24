<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Add Practical</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="add-practical-page">


    <!-- Header -->

    <div class="page-header">

        <div class="page-brand">

            <span class="page-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="page-separator">—</span>

            <span class="page-title">
                Add Practical
            </span>

        </div>

    </div>


    <!-- Main Content -->

    <div class="add-practical-content">

        <div class="add-practical-card">

            <div class="add-practical-icon">
                ➕
            </div>

            <h2>Add New Practical</h2>

            <p class="add-practical-description">
                Create a new practical assignment for students.
            </p>


            <form action="${pageContext.request.contextPath}/addPractical"
                  method="post">


                <input type="hidden"
                       name="subjectId"
                       value="1">


                <label for="practicalNo">
                    Practical Number
                </label>

                <input type="number"
                       id="practicalNo"
                       name="practicalNo"
                       placeholder="Enter practical number"
                       required>


                <label for="title">
                    Practical Title
                </label>

                <input type="text"
                       id="title"
                       name="title"
                       placeholder="Enter practical title"
                       required>


                <label for="description">
                    Description
                </label>

                <textarea id="description"
                          name="description"
                          rows="4"
                          placeholder="Enter practical description"
                          required></textarea>


                <label for="deadline">
                    Submission Deadline
                </label>

                <input type="date"
                       id="deadline"
                       name="deadline"
                       required>


                <button type="submit"
                        class="add-practical-button">

                    Add Practical

                </button>

            </form>


            <div class="add-practical-back">

                <a href="${pageContext.request.contextPath}/teacherDashboard">
                    ← Back to Dashboard
                </a>

            </div>

        </div>

    </div>


</body>

</html>