<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Submit Practical</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="submit-page">


    <!-- Header -->

    <div class="page-header">

        <div class="page-brand">

            <span class="page-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="page-separator">—</span>

            <span class="page-title">
                Submit Practical
            </span>

        </div>

    </div>


    <!-- Main Content -->

    <div class="submit-content">

        <div class="submit-card">

            <div class="submit-icon">
                📤
            </div>

            <h2>Submit Your Practical</h2>

            <p class="submit-description">
                Upload your practical PDF file.
            </p>


            <form action="${pageContext.request.contextPath}/submitPractical"
                  method="post"
                  enctype="multipart/form-data">


                <!-- Practical ID automatically received from Practicals page -->

                <input type="hidden"
                       name="practicalId"
                       value="<%= request.getParameter("practicalId") %>">


                <label for="file">
                    Practical PDF
                </label>

                <input type="file"
                       id="file"
                       name="file"
                       accept=".pdf,application/pdf"
                       required>


                <p style="font-size:13px; color:#718397; margin-top:8px;">
                    Only PDF files are allowed.
                </p>


                <button type="submit"
                        class="submit-practical-button">

                    📤 Submit Practical

                </button>

            </form>


            <div class="submit-back">

                <a href="${pageContext.request.contextPath}/practicals">
                    ← Back to Practicals
                </a>

            </div>

        </div>

    </div>


</body>

</html>