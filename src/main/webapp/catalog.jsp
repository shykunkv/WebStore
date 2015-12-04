<%@ taglib uri="/myTags" prefix="tag" %>
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
						<div class = "container-fluid">
							<div class="row">
								<div class="col-md-4"></div>
								<div class="col-md-8">
									<h1 class="cover-heading">
										<fmt:message key="catalog.heading" bundle="${lang}"/>
									</h1>
									<br>
									<br>
								</div>
							</div>
							<div class = "row">
								<div class="col-sm-4">
									<tag:categories/>
								</div>
								<div class="col-sm-8">
									<div class="row">
										<p><fmt:message key="lorem.short" bundle="${lang}"/></p>
									</div>
									<div class="row">
										<br>
										<br>
									</div>
									<div class="row">
										<div class="col-md-6">
											<fmt:message key="lorem.short" bundle="${lang}"/>
											<fmt:message key="lorem.short" bundle="${lang}"/>
										</div>
										<div class="col-md-6">
											<fmt:message key="lorem.short" bundle="${lang}"/>
											<fmt:message key="lorem.short" bundle="${lang}"/>
										</div>
									</div>
								</div>
							</div>
							<jsp:include page="add_category_modal.jsp"/>
							<jsp:include page="edit_category_modal.jsp"/>
						</div>
					</div>
					<div class="mastfoot">
						<jsp:include page="layouts/footer.jsp" />
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
