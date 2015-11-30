<%@ taglib uri="/myTags" prefix="tag" %>
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
				<div class = "container-fluid">
					<div class="row">
						<div class="col-md-6"></div>
						<div class="col-md-6">
							<h1 class="cover-heading">
								<c:out value="${product.name}"/>
							</h1><br/></br>
						</div>
					</div>
					<div class = "row">
						<div class="col-sm-5">
							<tag:categories/>
						</div>
						<div class="col-sm-1">
						</div>
						<div class = "col-xs-6">
							<div class="row">
							<div class = "col-xs-7">
								<img src = "images/<c:out value="${product.image}" />" alt = "image" height="150"/>
							</div>
							<div class = "col-xs-5">
								<p>Name: <c:out value="${product.name}"/> </p>
								<p>Brand: <em>  <c:out value="${product.brand}"/> </em> </p>
								<p>Price: $  <c:out value="${product.price}"/> </p>
								<br/>

								<c:if test="${user != null}" >
									<form method = "POST" action = "/main">
										<input type="hidden" name="action" value="addToCart" />
										<input type="hidden" name="product_id" value="<c:out value="${product.id}"/>" />
										<input type = "submit" value = "Add to Cart" name = "submit" alt = "Add To Cart" class="btn btn-default"/>
									</form>
								</c:if>


								<br/>
								<br/>
							</div>
							</div>

							<div class="row">
							<h3>Product description:</h3>
							<p> <c:out value="${product.description	}"/></p>
								</div>
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

