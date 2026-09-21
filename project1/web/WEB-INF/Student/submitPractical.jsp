<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Submit Practical</title>
    </head>
    <body>

        <h1>Submit Practical</h1>

        <form action="#" method="post" enctype="multipart/form-data">

            <label for="practical">Select Practical:</label>
            <select id="practical" name="practical" required>
                <option value="">--Select Practical--</option>
                <option value="1">Practical 1</option>
                <option value="2">Practical 2</option>
                <option value="3">Practical 3</option>
            </select>

            <br><br>

            <label for="file">Upload Practical File:</label>
            <input type="file" id="file" name="file" required>

            <br><br>

            <button type="submit">Submit Practical</button>

        </form>

    </body>
</html>