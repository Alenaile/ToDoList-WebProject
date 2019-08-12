<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
    <title>Start</title>
    <link href="index.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <div class="container">
        <div class="header_inner">

            <span class="header_username">USER: GUEST </span>

            <nav class="nav_header">
                <a class="nav_link" href="/login.jsp">Login</a>
                <a class="nav_link" href="/registrate.jsp">Registrate</a>
            </nav>
        </div>
    </div>
</header>

<div class="intro">
    <div class="container">
        <div class="intro_error">
            <c:if test="${not empty errorMessage}">
                <p class="error"><c:out value="${errorMessage}"/></p>
            </c:if>
        </div>
    </div>
</div>


<footer>
    <div class="container">
        <div class="footer_inner">
            <div class="dev_info">
                Developed by Alena Dziadkova
            </div>
        </div>
    </div>
</footer>

</body>
</html>