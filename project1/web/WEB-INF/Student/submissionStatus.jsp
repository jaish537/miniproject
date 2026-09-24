<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Submission Status</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="submission-status-page">


    <!-- Header -->

    <div class="page-header">

        <div class="page-brand">

            <span class="page-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="page-separator">—</span>

            <span class="page-title">
                Submission Status
            </span>

        </div>

    </div>


    <!-- Main Content -->

    <div class="page-content">

        <div class="content-heading">

            <h2>📋 My Submission Status</h2>

            <p>
                Track the status of your submitted practical files.
            </p>

        </div>


        <div class="status-table-card">

            <table>

                <tr>

                    <th>Practical</th>
                    <th>File Name</th>
                    <th>Submitted On</th>
                    <th>Status</th>

                </tr>


                <%

                    ResultSet rs =
                            (ResultSet) request.getAttribute("submissions");

                    while (rs != null && rs.next()) {

                        String status =
                                rs.getString("status");

                %>


                <tr>

                    <td>
                        <strong>
                            <%= rs.getString("title") %>
                        </strong>
                    </td>


                    <td>
                        <%= rs.getString("file_name") %>
                    </td>


                    <td>
                        <%= rs.getTimestamp("submitted_at") %>
                    </td>


                    <td>

                        <span class="status-badge <%= status.toLowerCase() %>">

                            <%= status %>

                        </span>

                    </td>

                </tr>


                <%

                    }

                %>

            </table>

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