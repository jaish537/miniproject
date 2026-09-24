<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Review Submission</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="review-submission-page">


    <!-- Header -->

    <div class="page-header">

        <div class="page-brand">

            <span class="page-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="page-separator">—</span>

            <span class="page-title">
                Review Submission
            </span>

        </div>

    </div>


    <!-- Main Content -->

    <div class="review-content">

        <div class="review-card">


            <div class="review-icon">
                📝
            </div>


            <h2>Review Student Submission</h2>

            <p class="review-description">
                Check the submitted practical and provide your feedback.
            </p>


            <!-- Submission Details -->

            <div class="submission-details">

                <div class="detail-row">

                    <span class="detail-label">
                        Student Name
                    </span>

                    <strong>
                        <%= request.getAttribute("studentName") %>
                    </strong>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        Practical
                    </span>

                    <strong>
                        <%= request.getAttribute("practicalTitle") %>
                    </strong>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        Submitted On
                    </span>

                    <span>
                        <%= request.getAttribute("submittedOn") %>
                    </span>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        File Name
                    </span>

                    <span>
                        📄 <%= request.getAttribute("fileName") %>
                    </span>

                </div>


                <div class="detail-row">

                    <span class="detail-label">
                        Current Status
                    </span>

                    <span class="status-badge">
                        <%= request.getAttribute("status") %>
                    </span>

                </div>

            </div>


            <!-- Review Form -->

            <form action="<%= request.getContextPath() %>/reviewSubmission"
                  method="post">

                <input type="hidden"
                       name="submissionId"
                       value="<%= request.getAttribute("submissionId") %>">


                <label for="remark">
                    Teacher Remark
                </label>

                <textarea id="remark"
                          name="remark"
                          rows="5"
                          placeholder="Enter your feedback or remark..."
                          required></textarea>


                <div class="review-buttons">

                    <button type="submit"
                            name="status"
                            value="CHECKED"
                            class="approve-button">

                        ✓ &nbsp; Approve / Mark Checked

                    </button>


                    <button type="submit"
                            name="status"
                            value="RESUBMIT"
                            class="resubmit-button">

                        ↻ &nbsp; Request Resubmission

                    </button>

                </div>

            </form>


            <div class="review-back">

                <a href="${pageContext.request.contextPath}/submissions">
                    ← Back to Submissions
                </a>

            </div>


        </div>

    </div>

</body>

</html>