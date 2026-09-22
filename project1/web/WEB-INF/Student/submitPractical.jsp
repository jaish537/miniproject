<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>Submit Practical</title>
    </head>

    <body>

        <h1>Submit Practical</h1>

        <form action="${pageContext.request.contextPath}/submitPractical"
              method="post">

            <label for="practicalId">
                Practical ID:
            </label>

            <input type="number"
                   id="practicalId"
                   name="practicalId"
                   required>

            <br><br>

            <label for="fileName">
                File Name:
            </label>

            <input type="text"
                   id="fileName"
                   name="fileName"
                   placeholder="example.pdf"
                   required>

            <br><br>

            <button type="submit">
                Submit Practical
            </button>

        </form>

    </body>
</html>