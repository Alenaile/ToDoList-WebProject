<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="tasks" scope="session" type="java.util.List"/>
<c:set var="today" value="<%=new Date()%>"/>
<c:url value="${ConstantsJSP.RECYCLE_BIN_PAGE}" var="recycleBinJSP"/>
<c:url value='${ConstantsJSP.MAIN_PAGE}' var="activeJSP"/>
<c:url value='${ConstantsJSP.CONTROLLER}' var="controller"/>


<html>
<head>
    <title>Fixed Tasks Page</title>
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
            <a class="nav_link" href="<c:out value="${recycleBinJSP}"/>">Recycle Bin</a>
        </span>
        </div>

        <div class="tasks">
            <form name="choseTask" action="<c:out value="${controller}"/>" method="post"
                  enctype="multipart/form-data">
                <input type="hidden" name="<%=ConstantsJSP.KEY_SECTION %>" value="<%=ConstantsJSP.FIXED%>">

                <p>FIXED TASKS</p>
                <div class="intro_inner fixed">
                    <table>
                        <%@ include file="/WEB-INF/commonPages/tableHead.jsp" %>


                        <c:forEach items="${tasks}" var="task">
                            <c:if test="${task.section == ConstantsJSP.FIXED}">
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
                                                   value="<%=ConstantsJSP.FIXED%>">

                                            <c:choose>
                                                <c:when test="${not empty task.fileName}">

                                                    <a class="aDownload"
                                                       href="<c:url value="/controller?action=Download&section=FIXED&id=${task.id}"/>"
                                                       download="">${task.fileName}</a>
                                                    <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>"
                                                           value="Del">

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
                <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>" value="Remove">

            </form>
        </div>


        <%@ include file="/WEB-INF/commonPages/error.jsp" %>
    </div>
</div>

<%@ include file="/WEB-INF/commonPages/footer.jsp" %>

</body>
</html>
