<%@ page import="by.gsu.epamlab.model.constants.Constants" %>
<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="today" value="<%=new Date()%>"/>
<c:set var="tomorrow" value="<%=new Date(new Date().getTime()+ 60*60*24*1000)%>"/>

<jsp:useBean id="tasks" scope="session" type="java.util.List"/>

<c:url value="${ConstantsJSP.RECYCLE_BIN_PAGE}" var="recycleBinJSP"/>
<c:url value='${ConstantsJSP.FIXED_PAGE}' var="fixedJSP"/>
<c:url value='${ConstantsJSP.CONTROLLER}' var="controller"/>

<html>

<head>
    <title>User Page</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">

</head>

<body>
<%@ include file="/WEB-INF/commonPages/header.jsp" %>


<div class="intro">

    <div class="container">

        <div class="section">
            <a class="nav_link" href="<c:out value="${fixedJSP}"/>">Fixed</a>&nbsp;&nbsp;
            <a class="nav_link" href="<c:out value="${recycleBinJSP}"/>">Recycle Bin</a><%--&nbsp;&nbsp;--%>
        </div>

        <div class="tasks">

            <form name="choseTask" action="<c:out value="${controller}"/>" method="post" enctype="multipart/form-data">
                <input type="hidden" name="<%=ConstantsJSP.KEY_SECTION %>" value="<%=ConstantsJSP.ACTIVE%>">

                <div id="tabs">
                    <div>

                        <div class="intro_inner tab">
                            <span> Today <fmt:formatDate type="date" value="${today}"
                                                         pattern="<%=ConstantsJSP.SHORT_DATE_PATTERN%>"/></span>
                            <table>

                                <%@ include file="/WEB-INF/commonPages/tableHead.jsp" %>

                                <c:forEach items="${tasks}" var="task">
                                    <c:if test="${(task.date < today) && task.section == ConstantsJSP.ACTIVE}">
                                        <tr>
                                            <td>
                                                <input name="<%=ConstantsJSP.KEY_CHECK_BOX %>" type="checkbox"
                                                       value="${task.id}"/>
                                            </td>

                                            <td><fmt:formatDate type="date" value="${task.date}"
                                                                pattern="<%=ConstantsJSP.DATE_PATTERN%>"/></td>

                                            <td> ${task.description} </td>

                                            <td>
                                                <%@ include file="/WEB-INF/commonPages/fileActions.jsp" %>
                                            </td>
                                        </tr>
                                    </c:if>

                                </c:forEach>

                            </table>
                        </div>
                    </div>
                    <div>


                        <div class="intro_inner tab">
                            <span>Tomorrow <fmt:formatDate type="date" value="${tomorrow}"
                                                           pattern="<%=ConstantsJSP.SHORT_DATE_PATTERN%>"/></span>
                            <table>

                                <%@ include file="/WEB-INF/commonPages/tableHead.jsp" %>

                                <c:forEach items="${tasks}" var="task">
                                    <c:if test="${today.before(task.date) && tomorrow.after(task.date) && task.section == ConstantsJSP.ACTIVE}">
                                        <tr>
                                            <td>
                                                <input name="<%=ConstantsJSP.KEY_CHECK_BOX %>" type="checkbox"
                                                       value="${task.id}"/>
                                            </td>

                                            <td><fmt:formatDate type="date" value="${task.date}"
                                                                pattern="<%=ConstantsJSP.DATE_PATTERN%>"/></td>

                                            <td> ${task.description} </td>

                                            <td>
                                                <%@ include file="/WEB-INF/commonPages/fileActions.jsp" %>

                                            </td>
                                        </tr>
                                    </c:if>

                                </c:forEach>

                            </table>
                        </div>
                    </div>
                    <div>

                        <div class="intro_inner tab">
                            <span>Someday</span>
                            <table>

                                <%@ include file="/WEB-INF/commonPages/tableHead.jsp" %>

                                <c:forEach items="${tasks}" var="task">
                                    <c:if test="${tomorrow.before(task.date)&& task.section == ConstantsJSP.ACTIVE}">
                                        <tr>
                                            <td>
                                                <input name="<%=ConstantsJSP.KEY_CHECK_BOX %>" type="checkbox"
                                                       value="${task.id}"/>
                                            </td>

                                            <td><fmt:formatDate type="date" value="${task.date}"
                                                                pattern="<%=ConstantsJSP.DATE_PATTERN%>"/></td>

                                            <td> ${task.description} </td>

                                            <td>
                                                <%@ include file="/WEB-INF/commonPages/fileActions.jsp" %>
                                            </td>
                                        </tr>
                                    </c:if>

                                </c:forEach>

                            </table>
                        </div>
                    </div>
                    <div class="submits">
                        <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>"  value="Fix">
                        <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>"  value="Remove">
                    </div>
                </div>
            </form>
        </div>


        <div class="add">
            <form class="addTaskForm" action="<c:out value="${controller}"/>" method="post">
                <input type="hidden" name="<%=ConstantsJSP.KEY_SECTION%>" value="<%=ConstantsJSP.ACTIVE%>">
                <label> Describe your new task: <input type="text" name="<%=ConstantsJSP.KEY_NEW_TASK%>" value="" required/></label>
                <label> <input type="date" name=<%=ConstantsJSP.KEY_DATE%> value="" required/> </label>
                <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>" value="Add"/>

            </form>
        </div>

        <%@ include file="/WEB-INF/commonPages/error.jsp" %>

    </div>
</div>


<%@ include file="/WEB-INF/commonPages/footer.jsp" %>

</body>
</html>
