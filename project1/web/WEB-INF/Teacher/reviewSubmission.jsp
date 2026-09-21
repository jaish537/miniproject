<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review Submission</title>
    </head>
    <body>

        <h1>Review Student Submission</h1>

        <p>
            <strong>Student Name:</strong>
            ${submission.studentName}
        </p>

        <p>
            <strong>Practical:</strong>
            ${submission.practicalTitle}
        </p>

        <p>
            <strong>Submitted On:</strong>
            ${submission.submittedOn}
        </p>

        <p>
            <strong>Submitted File:</strong>
            <a href="#">View / Download File</a>
        </p>

        <br>

        <form action="#" method="post">

            <label for="remark">Teacher Remark:</label>
            <br>

            <textarea id="remark"
                      name="remark"
                      rows="5"
                      cols="50"
                      placeholder="Enter your remarks"
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