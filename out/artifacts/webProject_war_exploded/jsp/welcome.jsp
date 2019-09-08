<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
    <title>Start Page</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>

    <div class="container">
        <div class="header_inner">

            <span class="header_username">USER: GUEST </span>

            <nav class="nav_header">
                <a class="nav_link" href="<c:url value="/jsp/user/login.jsp"/>">Login</a>
                <a class="nav_link" href="<c:url value="/jsp/user/registrate.jsp"/>">Registrate</a>
            </nav>
        </div>
    </div>
</header>


<%@ include file="/WEB-INF/commonPages/footer.jsp" %>

</body>
</html>