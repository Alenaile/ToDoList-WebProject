<%@ page contentType="text/html;charset=UTF-8" %>

<header>
    <div class="container">
        <div class="header_inner">

            <label class="header_username"> USER: ${sessionScope.user.login} </label>

            <form name="logoutForm" method="post" action="<c:url value='/controller'/>" style="margin-bottom: 0px;">
                <input type="hidden" name="action" value="logout" />
                <nav class="nav_header">

                    <button type="submit" id="linkButton" class="nav_link">Logout</button>
                </nav>
            </form>
        </div>
    </div>
</header>