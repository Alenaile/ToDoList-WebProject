<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<c:url value='${ConstantsJSP.CONTROLLER}' var="controller"/>

<form name="fileActions" action="<c:out value="${controller}"/>" method="post"
      enctype="multipart/form-data">
    <input type="hidden" name="<%=ConstantsJSP.KEY_ID%>" value="${task.id}">
    <input type="hidden" name="<%=ConstantsJSP.KEY_SECTION %>"
           value="<%=ConstantsJSP.ACTIVE%>">
    <c:choose>
        <c:when test="${not empty task.fileName}">

            <a class="aDownload" href="/controller?action=Download&section=ACTIVE&id=${task.id}"
               download="">${task.fileName}</a>

            <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>" value="Del">

        </c:when>
        <c:otherwise>

            <input type="file"
                   name=<%=ConstantsJSP.KEY_FILE%> value="${task.file}">
            <input type="submit" name="<%=ConstantsJSP.KEY_ACTION%>" value="Upload">

        </c:otherwise>
    </c:choose>
</form>