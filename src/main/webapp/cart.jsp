<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/myTags" prefix="tag" %>

<html lang="en">
    <head>
        <jsp:include page="layouts/resources.jsp"/>
    </head>

    <style>
        .quantity {
            color: black;
        }

        .cart {
            width: 200px;
        }

        th, td, tr {
            padding: 5px;
        }
    </style>

    <body>
        <div class="site-wrapper">
            <div class="site-wrapper-inner">
                <div class="cover-container">
                    <div class="masthead clearfix">
                        <jsp:include page="layouts/header.jsp"/>
                    </div>
                    <div class="inner cover">
                        <tag:cart/>
                    </div>
                    <div class="mastfoot">
                        <jsp:include page="layouts/footer.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
