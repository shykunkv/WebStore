<%@ taglib uri="/myTags" prefix="tag" %>
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
				<div class = "container-fluid">
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-8">
							<h1 class="cover-heading">
								<c:out value="${product.name}"/>
							</h1><br/></br>
						</div>
					</div>
					<div class = "row">
						<div class="col-sm-4">
							<tag:categories/>
						</div>
						<div class = "col-sm-8">
							<div class="row">
							<div class = "col-xs-7">
								<img src = "images/<c:out value="${product.image}" />" alt = "image" height="150"/>
								<input type="hidden" id="pr_image" value="<c:out value="${product.image}"/>">
							</div>
							<div class = "col-xs-5">
								<p><fmt:message key="product.name" bundle="${lang}"/>: <c:out value="${product.name}"/> </p>
								<input type="hidden" id="pr_name" value="<c:out value="${product.name}"/>">
								<p><fmt:message key="product.brand" bundle="${lang}"/>: <em>  <c:out value="${product.brand}"/> </em> </p>
								<input type="hidden" id="pr_brand" value="<c:out value="${product.brand}"/>">
								<p><fmt:message key="product.price" bundle="${lang}"/>: $  <c:out value="${product.price}"/> </p>
								<input type="hidden" id="pr_price" value="<c:out value="${product.price}"/>">
								<br/>
								<c:if test="${user != null && user.role == 'ADMIN'}">
									<div class="row">
										<div class="col-md-4"></div>
										<div class="col-md-8">
											<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#editProduct">
												<fmt:message key="product.edit.button" bundle="${lang}"/>
											</button>
										</div>
									</div>
								</c:if>
								<c:if test="${user != null && user.role == 'USER'}" >
									<form method = "POST" action = "/main">
										<input type="hidden" name="action" value="addToCart" />
										<input type="hidden" name="product_id" value="<c:out value="${product.id}"/>" />
										<input type = "submit" value = "<fmt:message key="product.cart" bundle="${lang}"/>" name = "submit" alt = "Add To Cart" class="btn btn-default"/>
									</form>
								</c:if>


								<br/>
								<br/>
							</div>
							</div>

							<div class="row">
								<h3><fmt:message key="product.description" bundle="${lang}"/>:</h3>
								<p> <c:out value="${product.description	}"/></p>
								<input type="hidden" id="pr_description" value="<c:out value="${product.description}"/>">
							</div>

							<c:if test="${user != null && user.role == 'ADMIN'}">
							<jsp:include page="add_category_modal.jsp"/>
							<jsp:include page="edit_category_modal.jsp"/>


							<div class = "row">

								<div class="modal fade" id="editProduct" role="dialog">

										<div class="modal-dialog">

											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
												</div>

												<div class="modal-body">
													<form action="/main" method="POST">
														<input type="hidden" name="product_id" value="<c:out value="${product.id}" />" >
														<input type="text" name="name" class="name form-control" placeholder="<fmt:message key="product.add.name" bundle="${lang}"/>"/>
														<br>
														<input type="text" name="brand" class="brand form-control" placeholder="<fmt:message key="product.add.brand" bundle="${lang}"/>"/>
														<br>
														<input type="text" name="price" class="price form-control" placeholder="<fmt:message key="product.add.price" bundle="${lang}"/>"/>
														<br>
														<input type="text" name="image" class="image form-control" placeholder="<fmt:message key="product.add.image" bundle="${lang}"/>"/>
														<br>
														<textarea class="description form-control" name="description" id="message-text" placeholder="<fmt:message key="product.add.description" bundle="${lang}"/>"></textarea>
														<input type = "hidden" name = "action" value="editProduct"/>
														<button type="submit" class="btn btn-default"><fmt:message key="product.add.save" bundle="${lang}"/></button>
													</form>

													<form action="/main" method="post">
														<input type="hidden" name="product_id" value="<c:out value="${product.id}" />" >
														<input type="hidden" name="action" value="deleteProduct"/>
														<button type="submit" class="btn btn-default"><fmt:message key="product.del.button" bundle="${lang}"/></button>
													</form>
												</div>

											</div>

										</div>



									<script type="text/javascript">
										$('#editProduct').on('show.bs.modal', function (event) {
											//var button = $(event.relatedTarget);
											var name = $("#pr_name").val();
											var brand = $("#pr_brand").val();
											var price = $("#pr_price").val();
											var description = $("#pr_description").val();
											var image = $("#pr_image").val();
											//var desc = button.data('description');

											var modal = $(this);

											modal.find('.name').val(name);
											modal.find('.brand').val(brand);
											modal.find('.price').val(price);
											modal.find('.description').val(description);
											modal.find('.image').val(image);
											//odal.find('.old_name').val(name);
											//modal.find('.del_name').val(name);
											//modal.find('.description').val(desc);
										});
									</script>
								</div>
							</div>
							</c:if>

						</div>
					</div>
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

