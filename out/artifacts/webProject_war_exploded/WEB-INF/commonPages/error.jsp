<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="intro_error">
    <c:if test="${not empty errorMessage}">
        <p class="error"><c:out value="${errorMessage}"/></p>
    </c:if>
</div>