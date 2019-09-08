<%@ page import="by.gsu.epamlab.model.constants.Constants" %>
<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="today" value="<%=new Date()%>"/>

<jsp:useBean id="tasks" scope="session" type="java.util.List"/>

<c:url value="${ConstantsJSP.FIXED_PAGE}" var="fixedJSP"/>
<c:url value='${ConstantsJSP.MAIN_PAGE}' var="activeJSP"/>
<c:url value='${ConstantsJSP.CONTROLLER}' var="controller"/>
<html>

<head>
    <title>Recycle Bin</title>
    <link href="<c:url value="/css/index.css"/>" rel="stylesheet" type="text/css">

</head>

<body>
<%@ include file="/WEB-INF/commonPages/header.jsp" %>


<div class="intro">
    <div class="container">

        <div>
            <span>Today: <fmt:formatDate pattern="${ConstantsJSP.SHORT_DATE_PATTERN}" value="${today}"/></span>

            <span class="section">
            <a class="nav_link" href="<c:out value="${activeJSP}"/>">Active Tasks</a>&nbsp;&nbsp;
            <a class="nav_link" href="<c:out value="${fixedJSP}"/>">Fixed Tasks</a>
            </span>
        </div>

        <div class="tasks">
            <form name="choseTask" action="<c:out value="${controller}"/>" method="post" enctype="multipart/form-data">
                <input type="hidden" name="<%=ConstantsJSP.KEY_SECTION %>" value="<%=ConstantsJSP.RECYCLE_BIN%>">

                <p>RECYCLE BIN</p>
                <div class="intro_inner recycle">
                    <table>

                        <%@ include file="/WEB-INF/commonPages/tableHead.jsp" %>


                        <c:forEach items="${tasks}" var="task">
                            <c:if test="${task.section == ConstantsJSP.RECYCLE_BIN}">
                                <tr>
                                    <td>
                                        <input name="<%=ConstantsJSP.KEY_CHECK_BOX %>" type="checkbox"
                                               value="${task.id}"/>
                                    </td>

                                    <td><fmt:formatDate type="date" value="${task.date}"
                                                        pattern="<%=ConstantsJSP.DATE_PATTERN%>"/></td>

                                    <td> ${task.description} </td>

                                    <td>
                                        <form name="fileActions" action="<c:out value="${controller}"/>" method="post"
                                              enctype="multipart/form-data">
                                            <input type="hidden" name="<%=ConstantsJSP.KEY_ID%>" value="${task.id}">
                                            <input type="hidden" name="<%=ConstantsJSP.KEY_SECTION %>"
                                                   value="<%=ConstantsJSP.RECYCLE_BIN%>">

                                            <c:choose>
                                                <c:when test="${not empty task.fileName}">
                                                    <span>${task.fileName}</span>
                                                    <div class="submits">
                                                        <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>"
                                                               value="Download">
                                                        <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>"
                                                               value="Del">
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="<%=ConstantsJSP.NO_FILE %>"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>

                    </table>
                </div>
                <div class="submits">
                    <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>"  value="Recover">
                    <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>" value="Delete">
                    <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>"  value="Clear">
                </div>
            </form>
        </div>

    </div>

    <%@ include file="/WEB-INF/commonPages/error.jsp" %>

</div>
</div>
<%@ include file="/WEB-INF/commonPages/footer.jsp" %>

</body>
</html>
