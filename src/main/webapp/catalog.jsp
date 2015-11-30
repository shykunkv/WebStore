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
						<div class="col-md-6"></div>
						<div class="col-md-6">
							<h1 class="cover-heading">
								Hot products
							</h1><br/></br>
						</div>
					</div>
					<div class = "row">
						<div class="col-sm-5">
							<tag:categories/>
						</div>
						<div class="col-sm-1"></div>
						<div class="col-sm-6">
							<div class="row">
								<p>
									Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
								</p>
							</div>
							<div class="row">
								<br><br>
							</div>
							<div class="row">
								<div class="col-md-6">
									Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
								</div>
								<div class="col-md-6">
									Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
								</div>
							</div>
						</div>
					</div>


						<div class="modal fade" id="add" role="dialog">
							<form action="/main" method="POST">
							<div class="modal-dialog">

								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="name">Modal Header</h4>
									</div>

									<div class="modal-body">
										<input type="text" name="name" class="form-control" placeholder="Category name"/>
										<br>
										<textarea class="form-control" name="description" id="message-text" placeholder="Category description"></textarea>
										<input type = "hidden" name = "action" value="addCategory"/>
									</div>

									<div class="modal-footer">
										<button type="submit" class="btn btn-default">Save</button>
									</div>

								</div>

							</div>
							</form>
						</div>



						<div class="modal fade" id="edit" role="dialog">

								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="name1">Modal Header</h4>
										</div>

										<div class="modal-body">
											<form action="/main" method="POST">
											<input type="text" name="name" class="name form-control" placeholder="Category name"/>
											<br>
											<textarea class="description form-control" name="description" id="message-text-edit" placeholder="Category description"></textarea>
											<input type = "hidden" name = "action" value="editCategory"/>
											<input class="old_name" type="hidden" name="old_name"/>;<button type="submit" class="btn btn-default">Save</button>
											</form>
										</div>

										<div class="modal-footer">

											<form action="/main" method="POST">
												<input type="hidden" name="action" value="deleteCategory"/>
												<input class="del_name" type="hidden" name="name"/>;
												<button type="submit" class="btn btn-default">Delete</button>
											</form>
										</div>

									</div>

								</div>

						</div>


						<script type="text/javascript">
							$('#edit').on('show.bs.modal', function (event) {
								var button = $(event.relatedTarget);
								var name = button.data('name');
								var desc = button.data('description');

								var modal = $(this);

								modal.find('.name').val(name);
								modal.find('.old_name').val(name);
								modal.find('.del_name').val(name);
								modal.find('.description').val(desc);
							});
						</script>


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
