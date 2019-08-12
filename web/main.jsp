<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
    <link href="index.css" rel="stylesheet" type="text/css">
</head>
<body>

<header>
    <div class="container">


        <form action="logout" method="post">
            <div class="header_inner">

                <label class="header_username"> USER: ${user.login} </label>

                <nav class="nav_header">
                    <a class="nav_link" href="/index.jsp">Logout</a>
                </nav>

            </div>
        </form>

    </div>


</header>

<div class="intro">
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
