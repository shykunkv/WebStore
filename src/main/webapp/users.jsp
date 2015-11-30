<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <jsp:include page="layouts/resources.jsp" />
    <jsp:include page="layouts/resources.jsp" />
</head>

<body>
<div class="site-wrapper">
    <div class="site-wrapper-inner">
        <div class="cover-container">

            <div class="masthead clearfix">
                <jsp:include page="layouts/header.jsp" />
            </div>

            <div class="inner cover">
                <c:if test="${user != null && user.role == 'ADMIN'}">
                    <form action="/main" method="post">
                        <div class="row">
                            <input type="hidden" name="action" value="searchUser" />
                            <h2>Search for users:</h2>
                            <input type="text" name="login" class="form-control" placeholder="Login" />
                            <br>
                            <button type="submit" value="Search" class="btn btn-default">Search</button>
                        </div>
                        <div class="row"></div>
                    </form>
                            <c:if test="${founded_user == null}">
                                <h3>User not found</h3>
                            </c:if>

                            <c:if test="${founded_user != null}" >
                                <p>ID: <c:out value="${founded_user.id}"/></p>
                                <p>Login: <c:out value="${founded_user.login}"/></p>
                                <p>Mail: <c:out value="${founded_user.mail}"/></p>

                                <c:if test="${founded_user.role == 'USER'}">
                                    <form action="/main" method="post">
                                        <input type="hidden" name="action" value="banAction"/>
                                        <input type="hidden" name="login" value="<c:out value="${founded_user.login}"/> "/>
                                        <button type="submit" class="btn btn-default">Ban user</button>
                                    </form>
                                </c:if>

                                <c:if test="${founded_user.role == 'BLOCKED'}">
                                    <form action="/main" method="post">
                                        <input type="hidden" name="action" value="unbanAction"/>
                                        <input type="hidden" name="login" value="<c:out value="${founded_user.login}"/> "/>
                                        <button type="submit" class="btn btn-default">Unban user</button>
                                    </form>
                                </c:if>
                            </c:if>

                        </div>

                </c:if>
                <c:if test="${user == null && user.role != 'ADMIN'}">
                    <h2>Please, leave this page :)</h2>
                </c:if>
            </div>

            <div class="mastfoot">
                <jsp:include page="layouts/footer.jsp" />
            </div>

        </div>
    </div>
</div>
</body>

</html>