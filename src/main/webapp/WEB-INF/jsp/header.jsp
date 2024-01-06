<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header>

    <nav>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <c:choose>
                    <c:when test="${sessionScope.user.role == 'ADMIN'}">
                        <div class="nav-item"><a href="/admin/adminHome">Home</a></div>
                    </c:when>
                    <c:when test="${sessionScope.user.role == 'BOSS'}">
                        <div class="nav-item"><a href="/boss/bossHome">Home</a></div>
                    </c:when>
                    <c:when test="${sessionScope.user.role == 'WORKER'}">
                        <div class="nav-item"><a href="/worker/workerHome">Home</a></div>
                    </c:when>
                </c:choose>
            </c:when>
            <c:otherwise>
                <div class="nav-item"><a href="/">Home</a></div>
            </c:otherwise>
        </c:choose>
        <c:if test="${sessionScope.user == null}">
            <div class="nav-item"><a href="/login">Login</a></div>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <form action="/logout" method="post" id="logoutForm" style="display: none;">
            </form>
            <div class="nav-item">
                <c:out value="${sessionScope.user.name}"/>
                <a href="#" onclick="document.getElementById('logoutForm').submit(); return false;">(Logout)</a>
            </div>
        </c:if>
    </nav>
</header>

</body>
</html>
