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
								<c:out value="${name}"/>
							</h1><br/></br>
						</div>
					</div>
					<div class = "row">
						<div class="col-sm-4">
							<tag:categories/>
						</div>
						<div class="col-sm-8">
							<tag:category/>
						</div>
					</div>
					<c:if test="${user != null && user.role == 'ADMIN'}">
						<jsp:include page="add_category_modal.jsp"/>
						<jsp:include page="edit_category_modal.jsp"/>

					<div class="row">
						<div class="col-md-4"></div>
							<div class="col-md-8">
							<button type="button" class="btn btn-default" data-toggle="modal" data-target="#addProduct">
								<fmt:message key="product.add.button" bundle="${lang}"/>
							</button>
						</div>
					</div>

					<div class="modal fade" id="addProduct" role="dialog">
						<form action="/main" method="POST">
							<div class="modal-dialog">

								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>

									<div class="modal-body">
										<input type="text" name="name" class="form-control" placeholder="<fmt:message key="product.add.name" bundle="${lang}"/>"/>
										<br>
										<input type="text" name="brand" class="form-control" placeholder="<fmt:message key="product.add.brand" bundle="${lang}"/>"/>
										<br>
										<input type="text" name="price" class="form-control" placeholder="<fmt:message key="product.add.price" bundle="${lang}"/>"/>
										<br>
										<input type="text" name="image" class="form-control" placeholder="<fmt:message key="product.add.image" bundle="${lang}"/>"/>
										<br>
										<textarea class="form-control" name="description" id="message-text" placeholder="<fmt:message key="product.add.description" bundle="${lang}"/>"></textarea>
										<input type = "hidden" name = "action" value="addProduct"/>
										<input type = "hidden" name = "category" value="${name}"/>
										<button type="submit" class="btn btn-default"><fmt:message key="product.add.save" bundle="${lang}"/></button>
									</div>
								</div>

							</div>
						</form>
					</div>
					</c:if>

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
