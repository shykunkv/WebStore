<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
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
				<form action="/main" method="POST">
					<input type="hidden" name="action" value="register"/>

					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<input type="text" name="login" class="form-control" placeholder="Login"/>
						</div>
						<div class="col-md-4">
							<c:if test="${login_message != null}">
								<h4><span class="label label-danger">
								<c:out value='${login_message}'/>
								</span></h4>
							</c:if>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<input type="text" name="mail" class="form-control" placeholder="Email address"/>
						</div>
						<div class="col-md-4">
							<c:if test="${mail_message != null}">
								<h4><span class="label label-danger">
								<c:out value='${mail_message}'/>
								</span></h4>
							</c:if>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<input type="password" name="password" class="form-control" placeholder="Password"/>
						</div>
						<div class="col-md-4">
							<c:if test="${password_message != null}">
								<h4><span class="label label-danger">
								<c:out value='${password_message}'/>
								</span></h4>
							</c:if>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-md-5"></div>
						<div class="col-md-2">
							<input type="submit" value="Sign up" class="btn btn-default btn-block"/>
						</div>
						<div class="col-md-5"></div>
					</div>
				</form>
			</div>

			<div class="mastfoot">
				<jsp:include page="layouts/footer.jsp" />
			</div>

		</div>
	</div>
</div>
</body>

</html>
