<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="by.gsu.epamlab.constants.ConstantsJSP" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
    <link href="../../css/index.css" rel="stylesheet" type="text/css">

</head>

<body>


<div class="intro">
    <div class="container">


        <div class="form">

            <form name="loginForm" method="post" action="<c:url value='/controller'/>">
                <input type="hidden" name="action" value="login"/>
                <span>Enter your login and password to sing in</span>

                <div class="login">
                    Login:<input name=<%=ConstantsJSP.KEY_LOGIN%> value="" placeholder="enter your login" required/>
                </div>

                <br/>
                <div class="password">
                    Password:<input name=<%=ConstantsJSP.KEY_PASSWORD%> type="password" value=""
                                    placeholder="enter your password" required/>
                </div>
                <br/>
                <input type="submit" value="Log in"/>

                <div class="container intro_error">
                    <c:if test="${not empty errorMessage}">
                        <p class="error"><c:out value="${errorMessage}"/></p>
                    </c:if>
                </div>


            </form>
        </div>


        <div>Return to <a class="nav_link" href="<c:url value='/jsp/accountPages/welcome.jsp'/>">start page</a></div>
        <div>No account? <a class="nav_link" href="<c:url value='/jsp/accountPages/registrate.jsp'/>">Registration</a></div>


    </div>
</div>
<%@ include file="../commonPages/footer.jsp" %>


</body>
</html>


