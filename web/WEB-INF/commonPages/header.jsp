<%@ page contentType="text/html;charset=UTF-8" %>
<c:url value='${ConstantsJSP.CONTROLLER}' var="controller"/>

<header>
    <div class="container">
        <div class="header_inner">

            <label class="header_username"> USER: ${sessionScope.user.login} </label>

            <form name="logoutForm" method="post" action="<c:out value="${controller}"/>" style="margin-bottom: 0px;">
                <input type="hidden" name="<%=ConstantsJSP.KEY_ACTION%>" value="logout"/>
                <nav class="nav_header">

                    <button type="submit" id="linkButton" class="nav_link">LOGOUT</button>
                </nav>
            </form>
        </div>
    </div>
</header>