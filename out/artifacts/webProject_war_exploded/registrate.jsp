<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.gsu.epamlab.constants.Constants" %>
<%@ page import="by.gsu.epamlab.constants.ConstantsJSP" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>

<c:url value='/login.jsp' var="loginController"/>


<head>
    <title>Register</title>
    <link href="css/index.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="intro">

    <div class="container">
        <div class="form">

            <form method="post" action="<c:url value='/controller'/>">
                <input type="hidden" name="action" value="registration" />
               <span> New user registration</span>
                <div class="login">
                    <label>Login:<input name=<%=ConstantsJSP.KEY_LOGIN%> value="" placeholder="enter your login"/></label>
                </div>

                <span class="username_rules">Username rules
                    <div class="descr">
                    <ul>
                        <li>must be 3 to 20 characters in length</li>
                        <li>can only contain alphanumeric characters, numbers, underscore (_) and dot (.)</li>
                        <li>cannot start with underscore and dot</li>
                    </ul>
                    </div>
                </span>

                <div class="password">
                    <label>Password:<input name=<%=ConstantsJSP.KEY_PASSWORD%> type="password" value="" placeholder="enter your password"/></label>
                </div>

                <div class="password_rules">Password rules
                    <div class="descr">
                        <ul>
                            <li>must contain at least</li>
                            <ul>
                                <li>6 characters</li>
                                <li>1 number</li>
                                <li>1 upper case letter</li>
                                <li>1 lower case letter</li>
                                <li>1 special character (! _ @ # $ % ^ & *)</li>
                            </ul>

                            <li>Password must not contain any spaces</li>
                        </ul>
                    </div>
                </div>

                <input type="submit" value="Registrate"/>


                <div class="container intro_error">
                    <c:if test="${not empty errorMessage}">
                        <p class="error"><c:out value="${errorMessage}"/></p>
                    </c:if>
                </div>
            </form>
        </div>

        Already have an account? <a class="nav_link" href='<c:url value='/login.jsp'/>'>Log in</a>
    </div>


</div>
<%@ include file="jsp/commonPages/footer.jsp" %>

</body>
</html>
