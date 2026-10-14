<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Add Students</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="add-students-page">


    <!-- Header -->

    <div class="page-header">

        <div class="page-brand">

            <span class="page-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="page-separator">—</span>

            <span class="page-title">
                Add Students
            </span>

        </div>

    </div>


    <!-- Main Content -->

    <div class="submit-content">

        <div class="submit-card">

            <div class="submit-icon">
                👨‍🎓
            </div>

            <h2>Add Students via CSV</h2>

            <p class="submit-description">
                Upload a CSV file to add multiple students at once.
            </p>


            <form action="${pageContext.request.contextPath}/addStudents"
                  method="post"
                  enctype="multipart/form-data">


                <label for="csvFile">
                    Student CSV File
                </label>

                <input type="file"
                       id="csvFile"
                       name="csvFile"
                       accept=".csv,text/csv"
                       required>


                <p style="font-size:13px; color:#718397; margin-top:8px;">
                    Only CSV files are allowed.
                </p>


                <div style="margin-top:15px;">

        <strong>CSV Format:</strong>

        <p style="font-size:13px; color:#718397;">
        NAME,ROLL_NO,EMAIL,SEMESTER,SECTION
        </p>

       <p style="font-size:13px; color:#718397;">
        Example: Rahul Sharma,CS001,rahul@gmail.com,5,A
     </p>

    <p style="font-size:13px; color:#718397;">
        Temporary password for imported students:
        <b>student123</b>
    </p>

        </div>


                <button type="submit"
                        class="submit-practical-button">

                    📤 Upload Students

                </button>

            </form>


            <div class="submit-back">

                <a href="${pageContext.request.contextPath}/teacherDashboard">
                    ← Back to Dashboard
                </a>

            </div>

        </div>

    </div>


</body>

</html>