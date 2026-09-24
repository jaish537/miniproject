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
                Enter the practical ID and your practical file name
                to submit your work.
            </p>


            <form action="${pageContext.request.contextPath}/submitPractical"
                  method="post">


                <label for="practicalId">
                    Practical ID
                </label>

                <input type="number"
                       id="practicalId"
                       name="practicalId"
                       placeholder="Enter practical ID"
                       required>


                <label for="fileName">
                    File Name
                </label>

                <input type="text"
                       id="fileName"
                       name="fileName"
                       placeholder="Example: practical1.pdf"
                       required>


                <button type="submit"
                        class="submit-practical-button">

                    Submit Practical

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