<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="inner">
        <h3 class="masthead-brand">
            Django Store
        </h3>
        <nav>
            <ul class="nav masthead-nav">
                <li><a href="../index.jsp">Home</a></li>
                <li><a href="../catalog.jsp">Catalog</a></li>

                <c:if test="${user != null}">
                    <li><a href="#">My Cart</a></li>
                </c:if>

                <c:if test="${user == null}">
                    <li><a href="../login.jsp">Login</a></li>
                </c:if>

                <c:if test="${user != null}">
                    <li><a href="/main">Logout</a></li>
                </c:if>
            </ul>
        </nav>


    </div>