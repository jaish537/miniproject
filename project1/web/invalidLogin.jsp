<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">

    <title>Login Failed</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body class="login-error-page">

    <div class="login-error-container">

        <div class="login-error-card">

            <div class="login-error-icon">
                ⚠
            </div>

            <h2>Login Failed</h2>

            <p class="login-error-message">
                Invalid email or password.
            </p>

            <p class="login-error-help">
                Please check your credentials and try again.
            </p>

            <a class="login-error-button"
               href="${pageContext.request.contextPath}/login.jsp">
                ← Back to Login
            </a>

        </div>

    </div>

</body>

</html>