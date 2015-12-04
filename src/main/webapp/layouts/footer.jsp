<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.webstore" var="lang"/>

<div class="inner">
    <form>
        <span>&copy; <a href = "https://ua.linkedin.com/in/kostiantyn-shykun-49b337102"><fmt:message key="footer.name" bundle="${lang}"/></a> 2015 </span>
        <div class="form-group">
            <label for="option-1">
                <input type="radio" id="option-1" onchange="submit()" name="language" value="en" ${language == 'en' ? 'checked' : ''}>
                <span>EN</span>
            </label>
            <label  for="option-2">
                <input type="radio" id="option-2" onchange="submit()" name="language" value="ua" ${language == 'ua' ? 'checked' : ''}>
                <span>UA</span>
            </label>
        </div>
    </form>
</div>
