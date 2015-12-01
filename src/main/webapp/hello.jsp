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
                <h2>Welcome, <c:out value="${user.login}"/></h2>

            </div>

            <div class="mastfoot">
                <jsp:include page="layouts/footer.jsp" />
            </div>

        </div>
    </div>
</div>
</body>

</html>
