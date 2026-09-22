<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>Review Submission</title>
    </head>

    <body>

        <h1>Review Student Submission</h1>

        <p>
            <strong>Student Name:</strong>
            <%= request.getAttribute("studentName") %>
        </p>

        <p>
            <strong>Practical:</strong>
            <%= request.getAttribute("practicalTitle") %>
        </p>

        <p>
            <strong>Submitted On:</strong>
            <%= request.getAttribute("submittedOn") %>
        </p>

        <p>
            <strong>File Name:</strong>
            <%= request.getAttribute("fileName") %>
        </p>

        <p>
            <strong>Current Status:</strong>
            <%= request.getAttribute("status") %>
        </p>

        <hr>

        <form action="<%= request.getContextPath() %>/reviewSubmission"
              method="post">

            <input type="hidden"
                   name="submissionId"
                   value="<%= request.getAttribute("submissionId") %>">

            <label for="remark">
                Teacher Remark:
            </label>

            <br>

            <textarea id="remark"
                      name="remark"
                      rows="5"
                      cols="50"
                      required></textarea>

            <br><br>

            <button type="submit"
                    name="status"
                    value="CHECKED">
                Approve / Mark Checked
            </button>

            <button type="submit"
                    name="status"
                    value="RESUBMIT">
                Request Resubmission
            </button>

        </form>

    </body>
</html>