<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title> Operation with User</title>
    <link rel="stylesheet" type="text/css" href="/style/style.css">
<%--    <link rel="stylesheet" type="text/css" href="../../../css/default.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="../../../css/authorization.css">--%>


</head>
<body>
<jsp:include page="../header.jsp" />
<div class="sidenav">
    <a href="operationWithUser"> Operation with User</a>
    <br>
    <br>
    <a href="registration">Create new User</a>
</div>
<div class="main">



        <h2>Operation with users</h2>
        <!-- Таблиця користувачів -->
    <div class="table-container">
        <table>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <td>
                        <form action="/admin/deleteUser" method="post">
                            <input type="hidden" name="userId" value="${user.id}"/>
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>
    <div class="or-action"><a href="/admin/adminHome">←Home</a></div>

    </div>




</div>
</body>
</html>
