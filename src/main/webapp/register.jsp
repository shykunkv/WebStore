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
				<form action="/main" method="POST">
					<input type="hidden" name="action" value="register"/>
					<c:if test="${message ne null}">
						<c:out value='${message}'/>
					</c:if>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<input type="text" name="login" class="form-control" placeholder="Login"/>
						</div>
						<div class="col-md-4"></div>
					</div>
					<br />
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<input type="text" name="mail" class="form-control" placeholder="Email address"/>
						</div>
						<div class="col-md-4"></div>
					</div>
					<br />
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<input type="password" name="password" class="form-control" placeholder="Password"/>
						</div>
						<div class="col-md-4"></div>
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
