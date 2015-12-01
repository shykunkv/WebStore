<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/myTags" prefix="tag" %>

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
						<div class = "container-fluid">
							<div class="row">
								<div class="col-md-4"></div>
								<div class="col-md-8">
									<h1 class="cover-heading">
										Hot products
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
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
									</div>
									<div class="row">
										<br>
										<br>
									</div>
									<div class="row">
										<div class="col-md-6">
											Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
											Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
										</div>
										<div class="col-md-6">
											Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
											Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
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
