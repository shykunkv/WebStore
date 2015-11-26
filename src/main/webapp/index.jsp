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
            <h1 class="cover-heading">Welcome to Django online store.</h1>
            <p class="lead">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua</p>
            <p class="lead"><a href="catalog.jsp" class="btn btn-lg btn-default"> Start now </a></p>
          </div>

          <div class="mastfoot">
            <jsp:include page="layouts/footer.jsp" />
          </div>

        </div>
      </div>
    </div>
  </body>

</html>
