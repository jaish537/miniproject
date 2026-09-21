<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Practical</title>
    </head>
    <body>

        <h1>Add New Practical</h1>

        <form action="#" method="post">

            <label for="subject">Subject:</label>
            <input type="text" id="subject" name="subject" required>

            <br><br>

            <label for="description">Description:</label>
            <textarea id="description" name="description"
                      rows="4" cols="40" required></textarea>

            <br><br>

            <label for="deadline">Submission Deadline:</label>
            <input type="date" id="deadline" name="deadline" required>

            <br><br>

            <button type="submit">Add Practical</button>

        </form>

    </body>
</html>