<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="inner">
        <h3 class="masthead-brand">
            Shykun Web Store
        </h3>
        <nav>
            <ul class="nav masthead-nav">
                <li><a href="../index.jsp">Home</a></li>

                <li><a href="/main?action=catalog">Catalog</a>
                </li>

                <c:if test="${user != null && user.role == 'USER'}">
                    <li><a href="../cart.jsp">
                        My Cart
                        <c:if test="${cart != null}">
                            <span class="badge">
                               ${cart.size}
                            </span>
                        </c:if>
                        <c:if test="${cart == null}">
                            <span class="badge">0</span>
                        </c:if>
                    </a></li>
                </c:if>

                <c:if test="${user != null && user.role == 'ADMIN'}">
                    <li><a href="../users.jsp">Users</a></li>
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