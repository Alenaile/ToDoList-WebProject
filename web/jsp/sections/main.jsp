<%@ page import="by.gsu.epamlab.constants.Constants" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="today" value="<%=new java.sql.Date(new java.util.Date().getTime())%>"/>
<c:set var="tomorrow" value="<%=new java.sql.Date(new java.util.Date().getTime() + 60*60*24*1000)%>"/>


<html>
<head>
    <title>User Page</title>
    <link href="../../css/index.css" rel="stylesheet" type="text/css">

</head>

<body>
<%@ include file="../commonPages/header.jsp" %>


<div class="intro">

    <div class="container">
            <div class="section">
                <a class="nav_link" href="fixed.jsp">Fixed</a> &nbsp;&nbsp;
                <a class="nav_link" href="recyclebin.jsp">Recycle Bin</a>
            </div>


        <div class="tasks">
            <form name="choseTask" action="<c:url value='/task'/>" method="post">

                <span> Today <fmt:formatDate type="date" value="${today}" pattern="dd.MM"/></span>
                <div class="intro_inner tab">
                    <table name="tab1">
                        <%@ include file="../commonPages/table.jsp" %>

                        <c:forEach items="${tasks}" var="task">
                            <c:if test="${(task.date < today) && task.section == 'ACTIVE'}">
                                <tr>
                                    <td>
                                        <input name="<%=Constants.CHECK_BOX_NAME %>" type="checkbox"
                                               value="${task.taskId}"/>
                                    </td>

                                    <td>${task.date}</td>

                                    <td> ${task.description} </td>

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

                <span>Tomorrow <fmt:formatDate type="date" value="${tomorrow}" pattern="dd.MM"/></span>

                <div class="intro_inner tab">
                    <table name="tab2">
                        <%@ include file="../commonPages/table.jsp" %>

                        <c:forEach items="${tasks}" var="task">
                            <c:if test="${today.before(task.date) && tomorrow.after(task.date) && task.section == 'ACTIVE'}">
                                <tr>
                                    <td>
                                        <input name="<%=Constants.CHECK_BOX_NAME %>" type="checkbox"
                                               value="${task.taskId}"/>
                                    </td>

                                    <td>${task.date}</td>

                                    <td> ${task.description} </td>

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

                <span> Someday</span>
                <div class="intro_inner tab">
                    <table name="tab1">
                        <%@ include file="../commonPages/table.jsp" %>

                        <c:forEach items="${tasks}" var="task">
                            <c:if test="${tomorrow.before(task.date)&& task.section == 'ACTIVE'}">
                                <tr>
                                    <td>
                                        <input name="<%=Constants.CHECK_BOX_NAME %>" type="checkbox"
                                               value="${task.taskId}"/>
                                    </td>

                                    <td>${task.date}</td>

                                    <td> ${task.description} </td>

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
                <input type="submit" name="action" value="Fix">
                <input type="submit" name="action" value="Remove">

            </form>
        </div>

        <div class="add">
            <form class="addTaskForm" action="<c:url value='/task'/>" method="post">
                <label> Describe your new task: <input type="text" name="newTask" value="" required/></label>
                <input type="date" name="date" value="" required/>
                <input type="submit" name="action" value="Add"/>
            </form>
        </div>


        <div class="intro_error">
            <c:if test="${not empty errorMessage}">
                <p class="error"><c:out value="${errorMessage}"/></p>
            </c:if>
        </div>
    </div>
</div>


<%@ include file="../commonPages/footer.jsp" %>

</body>
</html>
