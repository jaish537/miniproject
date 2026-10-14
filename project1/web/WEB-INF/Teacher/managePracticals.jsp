<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Manage Practicals</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="practicals-page">


<div class="page-header">

    <div class="page-brand">

        <span class="page-brand-icon">🎓</span>

        <span>PracTrack</span>

        <span class="page-separator">—</span>

        <span class="page-title">
            Manage Practicals
        </span>

    </div>

</div>



<div class="page-content">


    <div class="content-heading">

        <h2>📝 Manage Practicals</h2>

        <p>
            Edit or delete existing practical assignments.
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
                        (ResultSet) request.getAttribute(
                                "practicals"
                        );

                while (rs != null && rs.next()) {

                    long practicalId =
                            rs.getLong("practical_id");

                    int practicalNo =
                            rs.getInt("practical_no");

                    String title =
                            rs.getString("title");

                    String description =
                            rs.getString("description");

                    java.sql.Date deadline =
                            rs.getDate("deadline");
            %>


            <tr>

                <td>

                    <span class="practical-number">

                        <%= practicalNo %>

                    </span>

                </td>


                <td>

                    <strong>
                        <%= title %>
                    </strong>

                </td>


                <td>

                    <%= description %>

                </td>


                <td>

                    <%= deadline %>

                </td>


                <td>

                    <a class="submit-button"
                       href="<%= request.getContextPath() %>/editPractical?practicalId=<%= practicalId %>">

                        ✏️ Edit

                    </a>


                    &nbsp;


                    <a class="submit-button"
                       href="<%= request.getContextPath() %>/deletePractical?practicalId=<%= practicalId %>"
                       onclick="return confirm('Are you sure you want to delete this practical?');">

                        🗑️ Delete

                    </a>

                </td>

            </tr>


            <%
                }
            %>


        </table>

    </div>



    <div class="page-bottom">

        <a href="${pageContext.request.contextPath}/teacherDashboard">

            ← Back to Dashboard

        </a>

    </div>


</div>


</body>

</html>