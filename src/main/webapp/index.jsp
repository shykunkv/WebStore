<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <h1 class="cover-heading"><fmt:message key="index.welcome" bundle="${lang}"/></h1>
            <p class="lead"><fmt:message key="lorem.short" bundle="${lang}"/></p>
            <p class="lead"><a href="/main?action=catalog" class="btn btn-lg btn-default"><fmt:message key="index.start" bundle="${lang}"/></a></p>
          </div>

          <div class="mastfoot">
            <jsp:include page="layouts/footer.jsp" />
          </div>

        </div>
      </div>
    </div>
  </body>

</html>
