<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>PracTrack Login</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="login-page">


    <div class="login-container">


        <!-- Logo / Brand -->

        <div class="login-brand">

            <div class="login-icon">
                🎓
            </div>

            <h1>PracTrack</h1>

            <p>Practical File Submission & Verification</p>

        </div>


        <!-- Login Card -->

        <div class="login-card">

            <h2>Welcome Back</h2>

            <p class="login-subtitle">
                Login to continue to your dashboard
            </p>


            <form action="${pageContext.request.contextPath}/loginservlet"
                  method="post">


                <label for="email">
                    Email
                </label>

                <input type="email"
                       id="email"
                       name="email"
                       placeholder="Enter your email"
                       required>


                <label for="password">
                    Password
                </label>

                <input type="password"
                       id="password"
                       name="password"
                       placeholder="Enter your password"
                       required>


                <button type="submit"
                        class="login-button">

                    Login

                </button>


            </form>

        </div>


        <p class="login-footer">
            PracTrack &nbsp;•&nbsp; Practical Management System
        </p>


    </div>


</body>

</html>