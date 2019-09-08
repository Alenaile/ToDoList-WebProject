<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<c:url value="${ConstantsJSP.REGISTRATION_PAGE}" var="registrationJSP"/>
<c:url value='${ConstantsJSP.WELCOME_PAGE}' var="welcomeJSP"/>
<c:url value='${ConstantsJSP.CONTROLLER}' var="controller"/>

<head>
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">

</head>

<body>


<div class="intro">
    <div class="container">


        <div class="login_form">

            <form method="post" action="<c:out value="${controller}"/>">
                <input type="hidden" name="<%=ConstantsJSP.KEY_ACTION%>" value="login"/>
                <span class="title">Enter your login and password to sing in</span>

                <div class="login">
                    <span>Login:</span>
                    <input name=<%=ConstantsJSP.KEY_LOGIN%> value="" placeholder="enter your login" required/>
                </div>

                <div class="password">
                    <span>Password:</span>
                    <input name=<%=ConstantsJSP.KEY_PASSWORD%> type="password" value="" placeholder="enter your password" required/>
                </div>

                <input type="submit" value="Log in"/>

            </form>

            <div>Return to <a class="nav_link" href='<c:out value="${welcomeJSP}"/>'>start page</a></div>
            <div>No account? <a class="nav_link" href='<c:out value="${registrationJSP}"/>'>Registration</a></div>

            <%@ include file="/WEB-INF/commonPages/error.jsp" %>

        </div>
    </div>
</div>
<%@ include file="/WEB-INF/commonPages/footer.jsp" %>


</body>
</html>


