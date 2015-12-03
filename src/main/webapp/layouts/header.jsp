<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.webstore" var="lang"/>


<div class="inner">
        <h3 class="masthead-brand">
            <fmt:message key="header.title" bundle="${lang}"/>
        </h3>
        <nav>
            <ul class="nav masthead-nav">
                <li><a href="../index.jsp"><fmt:message key="header.home" bundle="${lang}"/></a></li>

                <li><a href="/main?action=catalog"><fmt:message key="header.catalog" bundle="${lang}"/></a>
                </li>

                <c:if test="${user != null && user.role == 'USER'}">
                    <li><a href="../cart.jsp">
                        <fmt:message key="header.cart" bundle="${lang}"/>
                        <c:if test="${cart != null}">
                            <span class="badge">
                               ${cart.size}
                            </span>
                        </c:if>
                        <c:if test="${cart == null}">
                            <span class="badge">0</span>
                        </c:if>
                    </a></li>
                </c:if>

                <c:if test="${user != null && user.role == 'ADMIN'}">
                    <li><a href="../users.jsp"><fmt:message key="header.users" bundle="${lang}"/></a></li>
                </c:if>


                <c:if test="${user == null}">
                    <li><a href="../login.jsp"><fmt:message key="header.login" bundle="${lang}"/></a></li>
                </c:if>

                <c:if test="${user != null}">
                    <li><a href="/main"><fmt:message key="header.logout" bundle="${lang}"/></a></li>
                </c:if>
            </ul>
        </nav>


    </div>