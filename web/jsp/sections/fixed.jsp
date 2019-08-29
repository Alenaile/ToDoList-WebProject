<%@ page import="by.gsu.epamlab.constants.Constants" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Fixed Tasks Page</title>
    <link href="<c:url value="/css/index.css"/>" rel="stylesheet" type="text/css">
</head>

<body>
<%@ include file="/jsp/commonPages/header.jsp" %>


<div class="intro">
    <div class="container date">
        <c:set var="now" value="<%=new Date()%>"/>
        <p>Today: <fmt:formatDate pattern="dd.MM" value="${now}"/></p>
    </div>

    <div class="container nav">
            <div class="section">
                <a class="nav_link" href="<c:url value="/jsp/sections/main.jsp"/>">Active Tasks</a>&nbsp;&nbsp;
                <a class="nav_link" href="<c:url value="/jsp/sections/recyclebin.jsp"/>">Recycle Bin</a>
            </div>
    </div>

    <div class="container tasks">
        <form name="choseTask" action="<c:url value='/task'/>" method="post">
            FIXED TASKS
            <div class="intro_inner tab">
                <table name="fixed_tasks">
                    <%@ include file="/jsp/commonPages/table.jsp" %>

                    <c:forEach items="${tasks}" var="task">
                        <c:if test="${task.section == 'FIXED'}">
                            <tr>
                                <td>
                                    <input name="<%=Constants.CHECK_BOX_NAME %>" type="checkbox"
                                           value="${task.taskId}"/>
                                </td>

                                <td>${task.date}</td>

                                <td>
                                        ${task.description}
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${not empty task.file}">
                                            ${task.file}
                                        </c:when>
                                        <c:otherwise>
                                            <input type="file" name="file" value="${task.file}">
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>

                        </c:if>
                    </c:forEach>

                </table>
            </div>
            <input type="submit" name="action" value="Remove">

        </form>
    </div>


    <div class="container intro_error">
        <c:if test="${not empty errorMessage}">
            <p class="error"><c:out value="${errorMessage}"/></p>
        </c:if>
    </div>

</div>

<%@ include file="/jsp/commonPages/footer.jsp" %>

</body>
</html>
