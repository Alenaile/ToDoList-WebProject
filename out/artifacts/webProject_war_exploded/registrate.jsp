<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Register</title>
    <link href="index.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="intro">

    <div class="container">
        <div class="form">
            <form action="registrate" method="post" >

                <div class="login">
                    <label>Login:<input name="login" value="" placeholder="enter your login"/></label>
                </div>

                <div class="password">
                    <label>Password:<input name="password" type="password" value=""
                                           placeholder="enter your password"/></label>
                </div>

                <input type="submit" value="Registrate"/>
            </form>


        </div>
    </div>
</div>

</body>


</html>
