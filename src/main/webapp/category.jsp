<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/myTags" prefix="tag" %>

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
								<c:out value="${name}"/>
							</h1><br/></br>
						</div>
					</div>
					<div class = "row">
						<div class="col-sm-5">
							<tag:categories/>
						</div>
						<div class="col-sm-1">
						</div>
						<div class="col-sm-6">
							<tag:category/>
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
