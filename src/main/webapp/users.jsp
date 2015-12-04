<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.webstore" var="lang"/>

<html lang="${language}">
<head>
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
                            <h2><fmt:message key="users.search.title" bundle="${lang}"/>:</h2>
                            <input type="text" name="login" class="form-control" placeholder="<fmt:message key="users.search.login" bundle="${lang}"/>" />
                            <br>
                            <button type="submit" value="Search" class="btn btn-default"><fmt:message key="users.search.button" bundle="${lang}"/></button>
                        </div>
                        <div class="row"></div>
                    </form>
                            <c:if test="${founded_user == null}">
                                <h3><fmt:message key="users.search.notfound" bundle="${lang}"/></h3>
                            </c:if>

                            <c:if test="${founded_user != null}" >
                                <p><fmt:message key="users.search.id" bundle="${lang}"/>: <c:out value="${founded_user.id}"/></p>
                                <p><fmt:message key="users.search.login" bundle="${lang}"/>: <c:out value="${founded_user.login}"/></p>
                                <p><fmt:message key="users.search.mail" bundle="${lang}"/>: <c:out value="${founded_user.mail}"/></p>

                                <c:if test="${founded_user.role == 'USER'}">
                                    <form action="/main" method="post">
                                        <input type="hidden" name="action" value="ban"/>
                                        <input type="hidden" name="login" value="<c:out value="${founded_user.login}"/> "/>
                                        <button type="submit" class="btn btn-default"><fmt:message key="users.ban.button" bundle="${lang}"/></button>
                                    </form>
                                </c:if>

                                <c:if test="${founded_user.role == 'BLOCKED'}">
                                    <form action="/main" method="post">
                                        <input type="hidden" name="action" value="unban"/>
                                        <input type="hidden" name="login" value="<c:out value="${founded_user.login}"/> "/>
                                        <button type="submit" class="btn btn-default"><fmt:message key="users.unban.button" bundle="${lang}"/></button>
                                    </form>
                                </c:if>
                            </c:if>

                        </div>

                </c:if>
                <c:if test="${user == null && user.role != 'ADMIN'}">
                    <h2><fmt:message key="users.error" bundle="${lang}"/></h2>
                </c:if>
            <div class="mastfoot">
                <jsp:include page="layouts/footer.jsp" />
            </div>

        </div>
    </div>
</div>
</body>

</html>