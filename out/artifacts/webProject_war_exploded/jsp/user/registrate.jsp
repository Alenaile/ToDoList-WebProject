<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.gsu.epamlab.model.constants.Constants" %>
<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>

<c:url value="${ConstantsJSP.LOGIN_PAGE}" var="loginJSP"/>
<c:url value='${ConstantsJSP.CONTROLLER}' var="controller"/>


<head>
    <title>Register</title>
    <link href="<c:url value="/css/index.css"/>" rel="stylesheet" type="text/css">
</head>
<body>

<div class="intro">

    <div class="container">
        <div class="registration_form">

            <form method="post" action="<c:out value="${controller}"/>">
                <input type="hidden" name="<%=ConstantsJSP.KEY_ACTION%>" value="registration"/>

                <span class="title">New user registration</span>

                <div class="login">
                    <label>Login:<input name=<%=ConstantsJSP.KEY_LOGIN%> value="" placeholder="enter your login"
                                        required/></label>
                </div>

                <%@ include file="/WEB-INF/commonPages/loginRules.jsp" %>

                <div class="password">
                    <label>Password:<input name=<%=ConstantsJSP.KEY_PASSWORD%> type="password" value=""
                                           placeholder="enter your password" required/></label>
                </div>

                <%@ include file="/WEB-INF/commonPages/passwordRules.jsp" %>

                <input type="submit" value="Registrate"/>

            </form>

            <div><span>Already have an account?</span> <a class="nav_link" href='<c:out value="${loginJSP}"/>'>Log
                in</a>
            </div>

            <%@ include file="/WEB-INF/commonPages/error.jsp" %>

        </div>


    </div>


</div>
<%@ include file="/WEB-INF/commonPages/footer.jsp" %>

</body>
</html>
