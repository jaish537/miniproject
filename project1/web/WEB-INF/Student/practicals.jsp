<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Available Practicals</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="practicals-page">


    <!-- Header -->

    <div class="page-header">

        <div class="page-brand">

            <span class="page-brand-icon">🎓</span>

            <span>PracTrack</span>

            <span class="page-separator">—</span>

            <span class="page-title">
                Available Practicals
            </span>

        </div>

       

    </div>


    <!-- Main Content -->

    <div class="page-content">

        <div class="content-heading">

            <h2>📚 Available Practicals</h2>

            <p>
                View practical assignments and submit your work.
            </p>

        </div>


        <div class="practical-table-card">

            <table>

                <tr>

                    <th>Practical No.</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Deadline</th>
                    <th>Action</th>

                </tr>


                <%

                    ResultSet rs =
                            (ResultSet) request.getAttribute("practicals");

                    while (rs != null && rs.next()) {

                %>


                <tr>

                    <td>
                        <span class="practical-number">
                            <%= rs.getInt("practical_no") %>
                        </span>
                    </td>


                    <td>
                        <strong>
                            <%= rs.getString("title") %>
                        </strong>
                    </td>


                    <td>
                        <%= rs.getString("description") %>
                    </td>


                    <td>
                        <%= rs.getDate("deadline") %>
                    </td>


                    <td>

                        <a class="submit-button"
                           href="<%= request.getContextPath() %>/submitPractical">

                            Submit

                        </a>

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